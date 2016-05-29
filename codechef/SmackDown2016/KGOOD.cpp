#include <iostream>
#include <cstring>
#include <vector>
#include <algorithm>
#include <cstdint>
using namespace std;
char word[100001], k;
int cnt[26];

int getSum(int, int, const int*);

int solve()
{
  cin >> word >> k;
  int length = strlen(word);
  memset(cnt, 0, sizeof cnt);
  for (int i = 0; i < length; i++)
    cnt[word[i] - 'a']++;

  vector<int> frequency;
  for (int i = 0; i < 26; i++)
    if (cnt[i] > 0)
      frequency.push_back(cnt[i]);

  sort(frequency.begin(), frequency.end());
  if (*frequency.rbegin() <= *frequency.begin() + k)
    return 0;

  int cache[frequency.size()];
  cache[0] = frequency[0];
  for (int i = 1; i < frequency.size(); i++)
    cache[i] = cache[i - 1] + frequency[i];

  int result = INT_MAX;
  for (int high = k + 1; high <= *frequency.rbegin(); high++) {
    int low = high - k;
    int left = lower_bound(frequency.begin(), frequency.end(), low) - frequency.begin() - 1;
    int right = upper_bound(frequency.begin(), frequency.end(), high) - frequency.begin();
    int temp = getSum(0, left, cache) + getSum(right, frequency.size() - 1, cache);
    temp -= high * (frequency.size() - right);
    result = min(result, temp);
  }

  return result;
}

int getSum(int low, int high, const int * cache)
{
  if (high < low)
    return 0;
  if (low == 0)
    return cache[high];
  else
    return cache[high] - cache[low - 1];
}

int main()
{
  int t;
  cin >> t;
  for (int tt = 0; tt < t; tt++)
    cout << solve() << endl;
}

