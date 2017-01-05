#include <iostream>
#include <algorithm>
#include <string>
#include <climits>

using namespace std;

#define MAXN 300

int n, k;
string words[MAXN];
int memo[MAXN][MAXN + 1];

int GetMaxCommonPrefix(const string& a, const string& b) {
  int length = 0;
  while (length < (int) a.size() && a[length] == b[length])
    ++length;
  return length;
}

void Solve(int test_number) {
  cin >> n >> k;
  for (int i = 0; i < n; ++i)
    cin >> words[i];
  sort(words, words + n);

  for (int i = 0; i < n; ++i) {
    for (int j = 0; j <= k; ++j)
      memo[i][j] = INT32_MAX;
    memo[i][1] = 2 * words[i].size() + 1;
  }

  for (int len = 2; len <= k; ++len) {
    for (int i = len - 1; i < n; ++i) {
      for (int j = len - 2; j < i; ++j) {
        int cost = memo[j][len - 1] - words[j].size() + words[i].size();
        cost += (words[j].size() + words[i].size() -
                 2 * GetMaxCommonPrefix(words[j], words[i])) + 1;
        memo[i][len] = min(memo[i][len], cost);
      }
    }
  }

  int result = INT32_MAX;
  for (int i = k - 1; i < n; ++i)
    result = min(result, memo[i][k]);

  printf("Case #%d: %d\n", test_number, result);
}

int main() {
  int tests;
  cin >> tests;
  for (int t = 1; t <= tests; ++t)
    Solve(t);

  return 0;
}
