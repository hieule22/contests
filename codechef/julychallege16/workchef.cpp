#include <iostream>
#include <cstdint>
#include <string>
#include <cstring>
 
using namespace std;
 
constexpr int TOTAL = 7 * 8 * 9;
constexpr int MASK = (1 << 9);
constexpr int divisor[] = {7, 8, 9};
constexpr int period[] = {72, 9 , 1};
 
int k;
string L, R;
int64_t dp[19][2][2][TOTAL][MASK];
 
inline bool HasDigit(int mask, int digit) {
  return mask & (1 << (digit - 1));
}
 
void GetMod(int mod, int rem[]) {
  for (int i = 0; i < 3; ++i) {
    rem[i] = mod / period[i];
    mod -= rem[i] * period[i];
  }
}
 
int GetMod(int rem[]) {
  int res = 0;
  for (int i = 0; i < 3; ++i)
    res += rem[i] * period[i];
  return res;
}
 
void GetFullMod(int rem[], int fullRem[]) {
  fullRem[1] = 0;
  fullRem[2] = rem[1] % 2;
  fullRem[3] = rem[2] % 3;
  fullRem[4] = rem[1] % 4;
  fullRem[5] = 0;
  int r2 = fullRem[2] ? fullRem[2] : 2;
  int r3 = fullRem[3] ? fullRem[3] : 3;
  fullRem[6] = (r2 * r3) % 6;
  fullRem[7] = rem[0];
  fullRem[8] = rem[1];
  fullRem[9] = rem[2];
}
 
uint64_t Count(int index, int larger, int smaller, int mod, int mask) {
  int rem[3];
  GetMod(mod, rem);
 
  if (dp[index][larger][smaller][mod][mask] >= 0)
    return dp[index][larger][smaller][mod][mask];
 
  int x = L[index] - '0';
  int y = R[index] - '0';
  uint64_t res = 0;
  for (int i = 0; i < 10; ++i) {
    if (x > i && !larger)
      continue;
    if (y < i && !smaller)
      break;
 
    int nRem[3];
    for (int j = 0; j < 3; ++j)
      nRem[j] = (rem[j] * 10 + i) % divisor[j];
    int nMask = i ? (mask | (1 << (i - 1))) : mask;
 
    if (index == R.size() - 1) {
      int fullRem[10];
      GetFullMod(nRem, fullRem);
      fullRem[5] = i % 5;
      int cnt = 0;
      for (int i = 1; i < 10; ++i)
        if (!fullRem[i] && HasDigit(nMask, i))
          cnt++;
      if (cnt >= k)
        res++;
      continue;
    }
 
    int nLarger = larger;
    int nSmaller = smaller;
    if (x < i)
      nLarger = 1;
    if (y > i)
      nSmaller = 1;
 
    int nMod = GetMod(nRem);
    res += Count(index + 1, nLarger, nSmaller, nMod, nMask);
  }
 
  return dp[index][larger][smaller][mod][mask] = res;
}
 
void Solve() {
  cin >> L >> R >> k;
  while (L.size() < R.size())
    L = "0" + L;
 
  for (int i = 0; i < R.size(); ++i)
    for (int j = 0; j < TOTAL; ++j)
      for (int k = 0; k < MASK; ++k)
        dp[i][0][0][j][k] = dp[i][1][0][j][k] = dp[i][0][1][j][k] =
            dp[i][1][1][j][k] = -1;
 
  uint64_t result = Count(0, 0, 0, 0, 0);
  cout << result << endl;
}
 
int main() {
  ios_base::sync_with_stdio(0);
  int q;
  cin >> q;
  for (int i = 0; i < q; ++i)
    Solve();
}