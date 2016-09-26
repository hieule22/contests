#include <algorithm>
#include <deque>
#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;
const int MAX_N = (1 << 18);
int a[2 * MAX_N], n;

void ErrorAndExit() {
  cout << "NO" << endl;
  exit(EXIT_SUCCESS);
}

void Fill(int spot, const int value, unordered_map<int, vector<int>>& levels, int level) {
  while (spot < 2 * n - 1) {
    a[spot] = value;
    ++level;
    if (2 * spot + 2 < 2 * n - 1) {
      levels[level].push_back(2 * spot + 2);
    }
    spot = 2 * spot + 1;
  }
}

int main() {
  cin >> n;
  vector<int> values;
  unordered_map<int, int> frequencies;
  for (int i = 0; i < 2 * n - 1; ++i) {
    cin >> a[i];
    if (frequencies.find(a[i]) == frequencies.end()) {
      values.push_back(a[i]);
    }
    frequencies[a[i]]++;
  }

  if (values.size() != n) {
    ErrorAndExit();
  }

  auto comparator = [&frequencies](int a, int b) {
    return frequencies[a] != frequencies[b] ? frequencies[a] > frequencies[b] : a < b;
  };
  sort(values.begin(), values.end(), comparator);

  unordered_map<int, vector<int>> levels;
  levels[0].push_back(0);
  int height = (int)log2(n);

  int index = 0;
  for (int i = 0; i <= height; ++i) {
    if (levels.find(i) == levels.end()) {
      ErrorAndExit();
    }

    if (i > 0) {
      if (levels[i].size() != (1 << (i - 1))) {
        ErrorAndExit();
      }
    }
    std::sort(levels[i].begin(), levels[i].end());
    for (int spot : levels[i]) {
      if (index >= values.size()) {
        ErrorAndExit();
      }
      if (height + 1 - i != frequencies[values[index]]) {
        ErrorAndExit();
      }
      if (spot > 0) {
        int parent = (spot - 1) / 2;
        if (a[parent] >= values[index]) {
          ErrorAndExit();
        }
      }
      Fill(spot, values[index], levels, i);
      ++index;
    }
  }

  cout << "YES" << endl;
  for (int i = 0; i < 2 * n - 1; ++i) {
    cout << a[i] << " ";
  }
  cout << endl;
}
