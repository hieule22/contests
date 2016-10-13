#include <iostream>
#include <string>
#include <bitset>

using namespace std;

bool contains(const string& s, char target) {
  for (char c : s) {
    if (c == target)
      return true;
  }
  return false;
}

void Solve() {
  string begin, middle, end;
  int n;
  cin >> begin >> middle >> end >> n;

  int result = 1;
  if (contains(end, '0')) {
    int index = end.size() - 1;
    while (index >= 0 && end[index] == '1')
      --index;
    for (; index >= 0; --index) {
      if (end[index] == '1')
        ++result;
    }

    int count = 0;
    for (char c : middle) {
      if (c == '1')
        count++;
    }
    result += count * n;

    for (char c : begin) {
      if (c == '1')
        ++result;
    }
  } else if (contains(middle, '0')) {
    int index = middle.size() - 1;
    while (index >= 0 && middle[index] == '1')
      --index;
    int suffix = middle.size() - index;
    int prefix = 0;
    for (; index >= 0; --index) {
      if (middle[index] == '1')
        prefix++;
    }
    result += prefix + (n - 1) * (suffix + prefix);

    for (char c : begin) {
      if (c == '1')
        ++result;
    }
  } else {
    int index = begin.size() - 1;
    while (index >= 0 && begin[index] == '1')
      --index;
    for (; index >= 0; --index) {
      if (begin[index] == '1')
        ++result;
    }
  }

  cout << result << endl;
}

int main() {
  int test_count;
  cin >> test_count;
  for (int i = 0; i < test_count; ++i)
    Solve();
}
