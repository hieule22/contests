#include <cstdio>
#include <cstring>

#define N_SOURCES 3
#define MAX_N_FOOD 20

int target[N_SOURCES];
int n, foods[N_SOURCES][MAX_N_FOOD];

void Solve(int test_number) {
  for (int i = 0; i < N_SOURCES; ++i)
    scanf("%d", target + i);
  scanf("%d", &n);
  for (int i = 0; i < n; ++i)
    for (int j = 0; j < N_SOURCES; ++j)
      scanf("%d", &foods[i][j]);

  int max_mask = (1 << n);
  int sums[N_SOURCES];
  for (int mask = 0; mask < max_mask; ++mask) {
    memset(sums, 0, sizeof sums);
    for (int i = 0; i < n; ++i) {
      if (mask & (1 << i)) {
        for (int j = 0; j < N_SOURCES; ++j)
          sums[j] += foods[i][j];
      }
    }

    bool equal = true;
    for (int i = 0; i < N_SOURCES; ++i)
      equal = (equal && (sums[i] == target[i]));
    if (equal) {
      printf("Case #%d: yes\n", test_number);
      return;
    }
  }
  printf("Case #%d: no\n", test_number);
}

int main() {
  int tests;
  scanf("%d", &tests);
  for (int test_number = 1; test_number <= tests; ++test_number)
    Solve(test_number);
  return 0;
}
