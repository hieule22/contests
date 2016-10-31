#include <iostream>
#include <cstdint>
#include <climits>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

const int MAX_N = 750;
int a[MAX_N];
int64_t minGuess[MAX_N + 1];
int n, m;

int analyze(int64_t guess) {
  priority_queue<int, vector<int>, greater<int>> qualityQueue;
  int result = 0;
  for (int i = 0; i < n; ++i) {
    if (a[i] < 0)
      qualityQueue.push(a[i]);
    guess += a[i];
    if (guess < 0) {
      ++result;
      int minQuality = qualityQueue.top();
      qualityQueue.pop();
      guess -= minQuality;
    }
  }
  return result;
}

int main() {
  cin >> n >> m;
  int minValue = INT32_MAX;
  for (int i = 0; i < n; ++i) {
    cin >> a[i];
    minValue = min(minValue, a[i]);
  }

  for (int removals = 0; removals <= n; ++removals) {
    int64_t low = 0, high = -1LL * minValue * n;
    while (low < high) {
      int64_t mid = low + (high - low) / 2;
      int temp = analyze(mid);
      if (temp > removals)
        low = mid + 1;
      else
        high = mid;
    }
    minGuess[removals] = low;
  }

  int64_t guess;
  for (int i = 0; i < m; ++i) {
    cin >> guess;
    int low = 0, high = n;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (minGuess[mid] <= guess)
        high = mid;
      else
        low = mid + 1;
    }
    cout << low << endl;
  }
}
