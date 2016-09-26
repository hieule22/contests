#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_map>

using namespace std;
const int MAX_N = (1 << 19);
int a[2 * MAX_N - 1];
size_t n;

void Fill(size_t spot, const int value,
          unordered_map<int, vector<int>>& levels, int height) {
  while (spot < 2 * n - 1) {
    a[spot] = value;
    ++height;
    if (2 * spot + 2 < 2 * n - 1) {
      levels[height].push_back(2 * spot + 2);
    }
    spot = 2 * spot + 1;
  }
}

int log2(int num) {
  int result = 0;
  while (num > 0) {
    ++result;
    num >>= 1;
  }
  return result - 1;
}

int main() {
  cin >> n;
  vector<int> values;
  unordered_map<int, int> frequencies;
  int value;
  for (size_t i = 0; i < 2 * n - 1; ++i) {
    cin >> value;
    if (frequencies.find(value) == frequencies.end()) {
      values.push_back(value);
    }
    frequencies[value]++;
  }

  if (values.size() != n) {
    // cerr << "Size mismatch" << endl;
    cout << "NO" << endl;
    return 0;
  }

  auto comparator = [&frequencies](int a, int b) {
    return frequencies[a] != frequencies[b] ? frequencies[a] > frequencies[b] : a < b;
  };
  sort(values.begin(), values.end(), comparator);

  const int height = log2(n);
  int current_height = 0;
  size_t index = 0;
  while (index < values.size()) {
    const int value = values[index];
    const int iterations = (current_height == 0) ? 1 : (1 << (current_height - 1));
    const int expected_frequency = height + 1 - current_height;
    for (int i = 0; i < iterations; ++i) {
      if (frequencies[value] != expected_frequency) {
        // cerr << "Expected: " << expected_frequency << " Actual: "
        //      << frequencies[value] << endl;
        cout << "NO" << endl;
        return 0;
      }
      ++index;
    }
    ++current_height;
  }

  unordered_map<int, vector<int>> levels;
  levels[0].push_back(0);
  index = 0;
  for (int i = 0; i <= height; ++i) {
    sort(levels[i].begin(), levels[i].end());
    for (int spot : levels[i]) {
      int value = values[index];
      if (spot > 0) {
        int parent = (spot - 1) / 2;
        if (a[parent] >= value) {
          // cerr << "Parent: " << a[parent] << " Child: " << value;
          cout << "NO" << endl;
          return 0;
        }
      }
      Fill(spot, value, levels, i);
      ++index;
    }
  }

  cout << "YES" << endl;
  for (size_t i = 0; i < 2 * n - 1; ++i) {
    cout << a[i] << " ";
  }
  cout << endl;
}
