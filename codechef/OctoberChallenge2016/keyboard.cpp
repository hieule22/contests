#include <cstdio>
#include <cmath>

void solve() {
  int n, m, c;
  scanf("%d %d %d", &n, &m, &c);
  int limit = (int)sqrt(c);
  int count = 0;
  for (int i = 1; i <= limit; ++i) {
    if (c % i == 0) {
      int j = c / i;
      if (i <= n && j <= m)
	++count;
      if (i != j && i <= m && j <= n)
	++count;
    }
  }

  printf("%d\n", count);
}

int main() {
  int test_count;
  scanf("%d", &test_count);
  for (int i = 0; i < test_count; ++i)
    solve();

  return 0;
}
