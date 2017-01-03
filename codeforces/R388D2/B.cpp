#include <cstdio>

int main() {
  int x[3], y[3];
  for (int i = 0; i < 3; ++i)
    scanf("%d %d", x + i, y + i);

  // Select the opposite vertex.
  printf("3\n");
  for (int i = 0; i < 3; ++i) {
    int sumX = 0, sumY = 0;
    for (int j = 0; j < 3; ++j) {
      if (i != j) {
        sumX += x[j];
        sumY += y[j];
      }
    }
    printf("%d %d\n", sumX - x[i], sumY - y[i]);
  }

  return 0;
}
