#include <iostream>

using namespace std;

int n, ans[302];

int main() {
  cin >> n;
  ans[0] = 1; ans[1] = 1; ans[2] = 2;
  for (int i = 3; i <= 301; ++i)
    ans[i] = ans[i - 3] + 2;
  cout << ans[n] << endl;
  
  if (n % 3 == 0) {
    int i = 0, j = n - ans[n] + 1;
    for (; i < ans[n]; ++i, j = (j + 1) % ans[n]) {
      int k = n - i - j;
      printf("%d %d %d\n", i, j, k);
    }
    return 0;
  }

  if (n % 3 == 1) {
    int i = 0, j = n - ans[n] + 1;
    for (; i < ans[n]; ++i, j = (j + 1) % ans[n]) {
      int k = n - i - j;
      printf("%d %d %d\n", i, j, k);
    }
    return 0;
  }

  if (n % 3 == 2) {
    int i = 0, j = (n + 1) - ans[n + 1] + 1;
    for (; i < ans[n + 1]; ++i, j = (j + 1) % ans[n + 1]) {
      int k = n + 1 - i - j;
      if (k > 0)
        printf("%d %d %d\n", i, j, k - 1);
    }
    return 0;
  }
}
