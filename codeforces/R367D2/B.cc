#include <iostream>
#include <algorithm>
#include <cstdint>

using namespace std;

int main() {
  int n, q;
  cin >> n;
  uint64_t price[n];
  for (int i = 0; i < n; ++i)
    cin >> price[i];
  sort(price, price + n);

  cin >> q;
  uint64_t m;
  for (int i = 0; i < q; ++i) {
    cin >> m;
    if (m < price[0]) {
      cout << 0 << endl;
      continue;
    }
    if (m > price[n - 1]) {
      cout << n << endl;
      continue;
    }
    int low = 0, high = n - 1;
    while (low < high) {
      int mid = low + ((high - low + 1) >> 1);
      if (price[mid] <= m)
        low = mid;
      else   // price[mid] > m
        high = mid - 1;
    }
    cout << low + 1 << endl;
  }
}
