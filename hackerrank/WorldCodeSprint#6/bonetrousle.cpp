#include <iostream>
#include <cstdint>

using namespace std;

const int B = 1e5;
uint64_t n, k, a[B + 2];
int b;

int main() {
  ios_base::sync_with_stdio(0);
  int t;
  cin >> t;
  for (int tt = 0; tt < t; ++tt) {
    cin >> n >> k >> b;
    uint64_t total = 0;
    for (int i = 1; i <= b; ++i) {
      a[i] = i;
      total += i;
    }

    if (total > n) {
      cout << -1 << endl;
      continue;
    }

    a[b + 1] = k + 1;
    bool solved = false;
    for (int i = b; i >= 1; --i) {
      uint64_t temp = a[i];
      a[i] = min(a[i + 1] - 1, a[i] + n - total);
      total += (a[i] - temp);
      if (total == n) {
        solved = true;
        break;
      }
    }

    if (solved) {
      for (int i = 1; i <= b - 1; ++i)
        cout << a[i] << " ";
      cout << a[b] << endl;
    } else {
      cout << -1 << endl;
    }
  }
}
