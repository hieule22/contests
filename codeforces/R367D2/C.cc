#include <iostream>
#include <cstdint>
#include <vector>
#include <string>
#include <climits>

#define IMPOSSIBLE UINT64_MAX

using namespace std;

template<typename MaxIterator, typename MinIterator>
bool Greater(MaxIterator max_begin, MaxIterator max_end,
             MinIterator min_begin, MinIterator min_end) {
  MaxIterator max_iter = max_begin;
  MinIterator min_iter = min_begin;
  while (max_iter != max_end && min_iter != min_end) {
    if (*max_iter > *min_iter)
      return true;
    if (*max_iter < *min_iter)
      return false;
    ++max_iter;
    ++min_iter;
  }
  if (max_iter == max_end && min_iter == min_end)
    return true;
  return min_iter == min_end;
}

int main() {
  int n;
  cin >> n;
  int c[n];
  for (int i = 0; i < n; ++i)
    cin >> c[i];
  vector<string> s;
  s.reserve(n);
  string temp;
  for (int i = 0; i < n; ++i) {
    cin >> temp;
    s.emplace_back(move(temp));
  }

  uint64_t dp[n][2];
  dp[0][0] = 0;
  dp[0][1] = c[0];
  for (int i = 1; i < n; ++i) {
    dp[i][0] = IMPOSSIBLE;
    if (dp[i - 1][0] != IMPOSSIBLE &&
        Greater(s[i].begin(), s[i].end(), s[i - 1].begin(), s[i - 1].end()))
      dp[i][0] = min(dp[i][0], dp[i - 1][0]);
    if (dp[i - 1][1] != IMPOSSIBLE &&
        Greater(s[i].begin(), s[i].end(), s[i - 1].rbegin(), s[i - 1].rend()))
      dp[i][0] = min(dp[i][0], dp[i - 1][1]);

    dp[i][1] = IMPOSSIBLE;
    if (dp[i - 1][0] != IMPOSSIBLE &&
        Greater(s[i].rbegin(), s[i].rend(), s[i - 1].begin(), s[i - 1].end()))
      dp[i][1] = min(dp[i][1], dp[i - 1][0] + c[i]);
    if (dp[i - 1][1] != IMPOSSIBLE &&
        Greater(s[i].rbegin(), s[i].rend(), s[i - 1].rbegin(), s[i - 1].rend()))
      dp[i][1] = min(dp[i][1], dp[i - 1][1] + c[i]);
  }

  uint64_t res = min(dp[n - 1][0], dp[n - 1][1]);
  if (res == IMPOSSIBLE)
    cout << -1 << endl;
  else
    cout << res << endl;
}
