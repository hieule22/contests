#include <iostream>
#include <cstring>
#include <cstdint>
#include <unordered_map>

using namespace std;
const uint64_t MOD = 1e9 + 7;
char s[100001], n;
int cnt[26];
uint64_t cache[100001];

int main() {
  int t;
  cin >> t;
  cache[0] = 1;
  for (int i = 1; i <= 100000; ++i) {
    cache[i] = cache[i - 1] * i % MOD;
  }
  for (int tt = 0; tt < t; ++tt) {
    cin >> s;
    n = strlen(s);
    memset(cnt, 0, sizeof cnt);
    int total = 0, order;
    for (int i = 0; i < n; ++i) {
      order = s[i] - 'a';
      if (!cnt[order])
	++total;
      ++cnt[order];
    }
    uint64_t result = cache[total];
    for (int i = 0; i < 26; ++i) {
      result = result * cache[cnt[i]] % MOD;
    }
    cout << result << endl;
  }
}
