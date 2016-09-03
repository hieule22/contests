#include <cstdint>
#include <algorithm>
#include <cstdio>
#include <climits>

using namespace std;

int64_t MIN = -1e9;
int64_t MAX = 1e9;
int n, m;
int64_t points[10000][100];
int64_t elements[10000];
int64_t prefix[10000];

int64_t calculate(int64_t value) {
  if (value < elements[0])
    return prefix[n - 1] - value * n;
  if (value > elements[n - 1])
    return value * n - prefix[n - 1];
  int low = 0, high = n - 1;
  while (low < high) {
    int mid = low + ((high - low + 1)) / 2;
    if (elements[mid] < value)
      low = mid;
    else
      high = mid - 1;
  }
  return (value * (low + 1) - prefix[low]) +
      (prefix[n - 1] - prefix[low] - value * (n - low - 1));
}

int64_t findMinimum(int index) {
  int64_t begin = MIN, end = MAX;
  // Preprocess.
  for (int i = 0; i < n; ++i)
    elements[i] = points[i][index];
  sort(elements, elements + n);
  // Compute prefix sums.
  prefix[0] = elements[0];
  for (int i = 1; i < n; ++i)
    prefix[i] = prefix[i - 1] + elements[i];

  while (begin + 1 < end) {
    int64_t low = (2 * begin + end) / 3;
    int64_t high = (begin + 2 * end) / 3;
    int64_t ll = calculate(low);
    int64_t hh = calculate(high);
    if (ll <= hh)
      end = high - 1;
    else
      begin = low + 1;
  }

  return (calculate(end) < calculate(begin)) ? end : begin;
}

int main() {
  scanf("%d %d", &n, &m);
  for (int i = 0; i < n; ++i)
    for (int j = 0; j < m; ++j)
      scanf("%lld", &points[i][j]);

  for (int i = 0; i < m; ++i) {
    printf("%lld ", findMinimum(i));
  }

  printf("\n");
}
