#include <cstdio>
#include <cstdint>
#include <unordered_map>

using namespace std;

#define MAXN 2000
int n, x[MAXN], y[MAXN];
int64_t dist[MAXN][MAXN];

void Solve(int test_number) {
  scanf("%d", &n);
  for (int i = 0; i < n; ++i)
    scanf("%d %d", x + i, y + i);
  for (int i = 0; i < n; ++i) {
    for (int j = i; j < n; ++j) {
      int64_t dx = x[i] - x[j], dy = y[i] - y[j];
      dist[i][j] = dist[j][i] = dx * dx + dy * dy;
    }
  }

  int cnt = 0;
  for (int i = 0; i < n; ++i) {
    unordered_map<int64_t, int> memo;
    for (int j = 0; j < n; ++j) {
      cnt += memo[dist[i][j]];
      memo[dist[i][j]]++;
    }
  }

  printf("Case #%d: %d\n", test_number, cnt);
}

int main() {
  int tests;
  scanf("%d", &tests);
  for (int tt = 1; tt <= tests; ++tt)
    Solve(tt);
  return 0;
}
