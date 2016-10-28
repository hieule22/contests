#include <cstdio>

int main() {
  int n, m;
  scanf("%d %d", &n, &m);
  
  int lastLoseSpot = 0;
  while (lastLoseSpot < n) {
    lastLoseSpot += (m + 1);
    while (__builtin_popcount(lastLoseSpot) & 1)
      lastLoseSpot++;
  }

  if (lastLoseSpot != n)
    printf("Alice\n");
  else
    printf("Bob\n");
}
