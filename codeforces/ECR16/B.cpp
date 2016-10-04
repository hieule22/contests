#include <iostream>
#include <cstdint>
#include <algorithm>

using namespace std;

const int MAX_N = 300005;
int64_t a[MAX_N], n;

int main() {
  cin >> n;
  int64_t total = 0;
  for (int i = 0; i < n; ++i) {
    cin >> a[i];
    total += a[i];
  }
  sort(a, a + n);
  int64_t sum = INT64_MAX, res = -1;
  int64_t prefix = 0, suffix = total;
  for (int i = 0; i < n; ++i) {
    prefix += a[i];
    suffix -= a[i];
    int64_t temp = (suffix - prefix) + a[i] * (2 * i + 2 - n);
    if (temp < sum) {
      res = i;
      sum = temp;
    }
  }
  cout << a[res] << endl;
}

