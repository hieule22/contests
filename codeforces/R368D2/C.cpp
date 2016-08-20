#include <iostream>
#include <cmath>
#include <cstdint>

using namespace std;

uint64_t n, m, k;

bool IsSquare(uint64_t a) {
  uint64_t sq = sqrt(a);
  return sq * sq == a;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin >> n;
  if (n <= 2) {
    cout << -1 << endl;
    return 0;
  }

  // n = 2ab
  if (n % 2 == 0) {
    uint64_t a = n / 2;
    m = a * a - 1;
    k = a * a + 1;
    cout << m << " " << k << endl;
    return 0;
  }

  // n = a^2 + b^2
  uint64_t limit = sqrt(n);
  for (uint64_t a = 1; a <= limit; ++a) {
    uint64_t b = n - a * a;
    if (IsSquare(b) && b > 0) {
      b = sqrt(b);
      if (a < b) swap(a, b);
      m = 2 * a * b;
      k = a * a - b * b;
      cout << m << " " << k << endl;
      return 0;
    }
  }

  // n = a^2 - b^2
  for (uint64_t i = 1; i <= limit; ++i) {
    if (n % i == 0) {
      uint64_t j = n / i;
      if ((i + j) % 2 == 0) {
        if (i < j) swap(i, j);
        uint64_t a = (i + j) / 2;
        uint64_t b = (i - j) / 2;
        m = 2 * a * b;
        k = a * a + b * b;
        cout << m << " " << k << endl;
        return 0;
      }
    }
  }

  cout << -1 << endl;
  return 0;
}
