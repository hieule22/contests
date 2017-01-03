#include <cstdio>

int main() {
  int n;
  scanf("%d", &n);

  // Case analysis.
  if (n < 4) {
    printf("1\n%d\n", n);
  } else if (n % 2 == 0) {
    printf("%d\n", n / 2);
    for (int i = 0; i < n / 2; ++i)
      printf("2 ");
    printf("\n");
  } else {
    printf("%d\n", (n - 3) / 2 + 1);
    for (int i = 0; i < (n - 3) / 2; ++i)
      printf("2 ");
    printf("3\n");
  }

  return 0;
}
