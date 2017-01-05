#include <cstring>
#include <cstdio>
#include <cstdlib>

#define min(a, b) ((a < b) ? a : b)
#define max(a, b) ((a > b) ? a : b)

char number[10];

void Solve(int test_number) {
  scanf("%s", number);
  int length = strlen(number);
  int max_val = atoi(number), min_val = atoi(number);
  for (int i = 0; i < length; ++i) {
    for (int j = i + 1; j < length; ++j) {
      char temp = number[i];
      number[i] = number[j];
      number[j] = temp;
      if (number[0] != '0') {
        max_val = max(max_val, atoi(number));
        min_val = min(min_val, atoi(number));
      }
      // Swap back.
      temp = number[i];
      number[i] = number[j];
      number[j] = temp;
    }
  }
  printf("Case #%d: %d %d\n", test_number, min_val, max_val);
}

int main() {
  int tests;
  scanf("%d", &tests);
  for (int t = 1; t <= tests; ++t)
    Solve(t);
  return 0;
}
