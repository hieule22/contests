#include <cstdio>
#include <cmath>

void solve() {
  int s, v;
  scanf("%d%d", &s, &v);
  double dist = M_PI * s / (3 * sqrt(3));
  double time = dist / v;
  printf("%.7f\n", time);
}

int main() {
  int test_count;
  scanf("%d", &test_count);
  for (int i = 0; i < test_count; ++i)
    solve();

  return 0;
}
