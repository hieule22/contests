#include <cstdio>
#include <climits>
#include <algorithm>

using namespace std;

#define INF 1000000000
#define MAXN 100000
#define SEQLENGTH 4
#define GAP 10

int n, problems[MAXN];

void Solve(int test_number) {
  scanf("%d", &n);
  for (int i = 0; i < n; ++i)
    scanf("%d", problems + i);

  // memo[i][j] gives minimum additions such that problem i has rank j
  // in its contest.
  int memo[n][SEQLENGTH + 1];
  // Base case.
  for (int i = 1; i <= SEQLENGTH; ++i)
    memo[0][i] = (problems[0] >= i) ? i - 1 : INF;

  for (int i = 1; i < n; ++i) {
    for (int j = 1; j <= SEQLENGTH; ++j) {
      memo[i][j] = INF;
      if (problems[i] < j) {
        continue;
      }

      for (int k = 1; k <= SEQLENGTH; ++k)
        memo[i][j] = min(memo[i][j], (memo[i - 1][k] + SEQLENGTH - k) + j - 1);
      int delta = problems[i] - problems[i - 1];
      for (int k = 1; k < j; ++k) {
        if (delta >= j - k && delta <= 10 * (j - k))
          memo[i][j] = min(memo[i][j], memo[i - 1][k] + (j - k - 1));
      }
    }
  }

  int res = INF;
  for (int i = 1; i <= SEQLENGTH; ++i)
    res = min(res, memo[n - 1][i] + SEQLENGTH - i);
  printf("Case #%d: %d\n", test_number, res);
}

int main() {
  int tests;
  scanf("%d", &tests);
  for (int t = 1; t <= tests; ++t)
    Solve(t);
  return 0;
}
