// O(nlogn) solution using binary search.

#include <cstdio>
#include <cstdint>

#define MAXN 100000

int n, target, boxes[MAXN];
uint64_t memo[MAXN];

uint64_t Sum(int begin, int end) {
  return begin == 0 ? memo[end] : memo[end] - memo[begin - 1];  
}

void Solve(int test_number) {
  scanf("%d %d", &n, &target);
  for (int i = 0; i < n; ++i)
    scanf("%d", boxes + i);
  memo[0] = boxes[0];
  for (int i = 1; i < n; ++i)
    memo[i] = memo[i - 1] + boxes[i];

  uint64_t result = 0;
  for (int i = 0; i < n; ++i) {
    if (boxes[i] > target)
      continue;
    int low = 0, high = i;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (Sum(mid, i) <= (uint64_t) target)
        high = mid;
      else
        low = mid + 1;
    }
    result += (i - low + 1);
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
