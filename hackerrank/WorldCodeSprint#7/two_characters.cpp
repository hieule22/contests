#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

const int ALPHABET_SIZE = 26;

int Analyze(const string& s, int first, int second) {
  char a = (char)('a' + first);
  char b = (char)('a' + second);
  bool hasA = false, hasB = false;
  int length = 0;
  for (char c : s) {
    if (c == a) {
      if (hasA) {
        return 0;
      }
      hasA = true;
      hasB = false;
      ++length;
    } else if (c == b) {
      if (hasB) {
        return 0;
      }
      hasB = true;
      hasA = false;
      ++length;
    }
  }

  return (length > 1) ? length : 0;
}

int main() {
  int n;
  string s;
  cin >> n >> s;

  int result = 0;
  for (int i = 0; i < ALPHABET_SIZE; ++i) {
    for (int j = i + 1; j < ALPHABET_SIZE; ++j) {
      result = max(result, Analyze(s, i, j));
    }
  }

  cout << result << endl;
  return 0;
}
