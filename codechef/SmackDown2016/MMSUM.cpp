#include <iostream>
#include <climits>
#include <cstdint>
#define max(a, b) ((a > b) ? a : b)
using namespace std;

int n, a[100000];
int64_t maxLeft[100000], maxRight[100000];

void solve()
{
  cin >> n;
  for (int i = 0; i < n; i++)
    cin >> a[i];
  maxLeft[0] = 0;
  for (int i = 1; i < n; i++) {
    if (maxLeft[i - 1] < 0)
      maxLeft[i] = a[i - 1];
    else
      maxLeft[i] = maxLeft[i - 1] + a[i - 1];
  }

  maxRight[n - 1] = 0;
  for (int i = n - 2; i >= 0; i--) {
    if (maxRight[i + 1] < 0)
      maxRight[i] = a[i + 1];
    else
      maxRight[i] = maxRight[i + 1] + a[i + 1];
  }

  int64_t result = INT64_MIN;
  for (int i = 0; i < n; i++) {
    result = max(result, a[i]);
    result = max(result, a[i] + maxRight[i]);
    result = max(result, a[i] + maxLeft[i]);
    result = max(result, a[i] + maxLeft[i] + maxRight[i]);
    result = max(result, maxLeft[i] + maxRight[i]);
  }

  cout << result << endl;
}

int main()
{
  ios_base::sync_with_stdio(0);
  int t;
  cin >> t;
  for (int tt = 0; tt < t; tt++)
    solve();
}
