#include <iostream>
#include <cstdint>
#include <cstring>
using namespace std;

uint64_t MOD = 1e9 + 7;

char s[1000005];
int last[26], cnt[26][1000000], length;
uint64_t ans[1000000], triple[1000000][26];

void UpdateCount(int order, int index) {
  for (int i = 0; i < 26; ++i) {
    if (i == order)
      cnt[i][index] = 1;
    else
      cnt[i][index] = 0;
    if (index > 0)
      cnt[i][index] += cnt[i][index - 1];
  }
}

int CountNumbers(int order, int begin, int end) {
  int ans = cnt[order][end];
  if (begin > 0)
    ans -= cnt[order][begin - 1];
  return ans;
}

uint64_t Choose2(uint64_t n) {
  if (n > 1) 
    return n * (n - 1) / 2;
  return 0;
}

uint64_t GetPairs(int begin, int end) {
  if (begin >= end)
    return 0;
  uint64_t res = 0;
  for (int i = 0; i < 26; ++i) {
    uint64_t cnt = CountNumbers(i, begin, end);
    res = (res + Choose2(cnt)) % MOD;
  }
  return res;
}

int main() {
  cin >> s;
  length = strlen(s);
  for (int i = 0; i < 26; ++i)
    last[i] = -1;
  uint64_t res = 0;
  for (int i = 0; i < length; ++i) {
    int order = s[i] - 'a';
    UpdateCount(order, i);
    if (last[order] == -1) {
      last[order] = i;  // Update last appearance
      ans[i] = 0;
      for (int j = 0; j < 26; ++j)
        triple[i][j] = 0;
      continue;
    }
    ans[i] = ans[last[order]];
    int previous = CountNumbers(order, 0, last[order]);
    for (int j = 0; j < 26; ++j) {
      triple[i][j] = triple[last[order]][j];
      triple[i][j] = (triple[i][j] + previous * CountNumbers(j, last[order] + 1, i - 1));
    }
    triple[i][order] = (triple[i][order] + previous - 1) % MOD;

    ans[i] = (ans[i] + Choose2(previous - 1)) % MOD;
    uint64_t pairs = GetPairs(last[order] + 1, i - 1);
    uint64_t temp = (previous * pairs) % MOD;
    ans[i] = (ans[i] + temp) % MOD;
    for (int j = 0; j < 26; ++j) {
      ans[i] = (ans[i] + triple[last[order]][j] * CountNumbers(j, last[order] + 1, i - 1)) % MOD;
    }
    last[order] = i;  // Update last appearance
    res = (res + ans[i]) % MOD;
  }
  cout << res << endl;
}

    

  
