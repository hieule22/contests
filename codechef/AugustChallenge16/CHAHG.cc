#include <iostream>
#include <cstdint>
#include <cmath>
#include <vector>
#include <climits>
#include <algorithm>
#include <assert.h>

using namespace std;

const int MAX_N = 10;
const int64_t INF = INT64_MAX;
int h[MAX_N], m[MAX_N], n;

using Interval = pair<int64_t, int64_t>;

bool operator< (const Interval& lhs, const Interval& rhs) {
  return lhs.first <= rhs.first;
}

bool GetInterval(const int minima, const int maxima,
		 int64_t* low, int64_t* high) {
  if (m[minima] == m[maxima]) {
    return h[minima] < h[maxima];
  }
  double time = (h[minima] - h[maxima]) * 1.0 / (m[maxima] - m[minima]);
  if (m[minima] > m[maxima]) {
    int64_t upper_bound = ceil(time - 1);
    if (upper_bound < *low) return false;
    *high = min(*high, upper_bound);
  } else {
    int64_t lower_bound = floor(time + 1);
    if (lower_bound > *high) return false;
    *low = max(*low, lower_bound);
  }
  return true;
}

void Solve() {
  cin >> n;
  for (int i = 0; i < n; ++i)
    cin >> h[i] >> m[i];

  bool possible[] = {true, true};
  int64_t low[] = {0, 0};
  int64_t high[] = {INF, INF};
  for (int i = 1; i < n; ++i) {
    for (int j = 0; j < 2; ++j) {
      bool exceed = (i % 2 == j);
      int minima = exceed ? i - 1 : i;
      int maxima = exceed ? i : i - 1;
      possible[j] = possible[j] &&
	GetInterval(minima, maxima, &low[j], &high[j]);
      assert(low[j] >= 0);
      assert(low[j] <= high[j]);
    }
  }

  vector<Interval> intervals;
  for (int i = 0; i < 2; ++i)
    if (possible[i])
      intervals.push_back({low[i], high[i]});

  std::sort(intervals.begin(), intervals.end());
  if (intervals.size() == 2) {
    if (intervals[0].second >= intervals[1].first) {
      intervals[0].second = max(intervals[0].second, intervals[1].second);
      intervals.pop_back();
    }
  }

  cout << intervals.size() << endl;
  for (Interval interval : intervals) {
    assert(interval.first <= interval.second);
    cout << interval.first << " ";
    if (interval.second == INF)
      cout << "Inf" << endl;
    else
      cout << interval.second << endl;
  }
}

int main() {
  ios_base::sync_with_stdio(0);
  int t;
  cin >> t;
  for (int tt = 0; tt < t; ++tt)
    Solve();
}
