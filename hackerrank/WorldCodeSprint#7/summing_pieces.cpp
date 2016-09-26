#include <iostream>
#include <cstdint>

using namespace std;

const uint64_t MAX_N = 1e6;
const uint64_t MOD = 1e9 + 7;

int n;
uint64_t power[MAX_N + 1], a[MAX_N + 1];

int main() {
  cin >> n;
  for (int i = 1; i <= n; ++i) {
    cin >> a[i];
  }
  
  power[0] = 1;
  for (int i = 1; i <= n; ++i)
    power[i] = power[i - 1] * 2 % MOD;

  uint64_t result = 0;

  // Compute all pieces that stretches to the left end.
  uint64_t total = 0;
  for (int i = 1; i <= n; ++i) {
    total = (total + a[i]) % MOD;
    uint64_t temp = total * i % MOD;
    if (n - i > 0)
      temp = temp * power[n - i - 1] % MOD;
    result = (result + temp) % MOD;
  }

  // Compute all pieces that stretches to the right end but does not touch
  // the left end.
  total = 0;
  for (int i = n; i > 1; --i) {
    total = (total + a[i]) % MOD;
    uint64_t temp = total * (n - i + 1) % MOD;
    if (i - 1 > 0)
      temp = temp * power[i - 2] % MOD;
    result = (result + temp) % MOD;
  }

  for (int m = 2; m < n; ++m) {
    uint64_t A = (power[n] + power[n - 1] + (MOD - power[n - m + 1])
                  + (MOD - (m * power[n - m] % MOD))) % MOD;
    uint64_t B = (power[m] + (MOD - 2)) % MOD;
    uint64_t C = ((n - m) * power[m - 1] + (power[m - 1] + MOD - 2) + (MOD - (n - 2))) % MOD;
    uint64_t temp = (A + (MOD - B) + (MOD - C)) % MOD;
    result = (result + a[m] * temp) % MOD;
  }

  cout << result << endl;  
  return 0;
}
