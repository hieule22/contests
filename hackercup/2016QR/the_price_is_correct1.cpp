// O(n) solution using two pointers.

#include <cstdio>
#include <cstdint>

#define MAXN 100000

int n, target, boxes[MAXN];

void Solve(int test_number) {
  scanf("%d %d", &n, &target);
  uint64_t result = 0, current = 0;
  int threshold = 0;
  for (int i = 0; i < n; ++i) {
    scanf("%d", boxes + i);
    current += boxes[i];
    while (threshold <= i && current > (uint64_t) target) {
      current -= boxes[threshold];
      ++threshold;
    }
    result += i - threshold + 1;
  }
  printf("Case #%d: %llu\n", test_number, result);
}

int main() {
  int tests;
  scanf("%d", &tests);
  for (int t = 1; t <= tests; ++t)
    Solve(t);
  return 0;
}
