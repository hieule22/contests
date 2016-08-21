#include <iostream>
#include <cstdint>
#include <vector>
#include <string>
#include <unordered_map>
using namespace std;
 
constexpr int TOTAL = 362880;
int k;
string sL, sR;
int64_t dp[19][2][2][TOTAL][512];
 
inline bool HasDigit(int mask, int digit) {
	return mask & (1 << (digit - 1));
}
 
void GetMod(int mod, int rem[10]) {
	int period = TOTAL;
	for (int i = 1; i < 10; ++i) {
		rem[i] = mod / period;
		mod -= rem[i] * period;
		period /= i + 1;
	}
}
 
int GetMod(int rem[10]) {
	int res = 0;
	int period = 1;
	for (int i = 9; i > 0; --i) {
		res += rem[i] * period;
		period *= i;
	}
	return res;
}
 
uint64_t Count(int index, int larger, int smaller, int mod, int mask) {
	int rem[10];
	GetMod(mod, rem);
	if (index == sR.size()) {
		int cnt = 0;
		for (int i = 1; i < 10; ++i)
			if (rem[i] == 0 && HasDigit(mask, i))
				cnt++;
		if (cnt >= k) {
			for (int i = 1; i < 10; ++i)
				cout << rem[i] << " ";
			cout << endl;
		}
		return (cnt >= k) ? 1 : 0;
	}
 
	if (dp[index][larger][smaller][mod][mask] >= 0)
		return dp[index][larger][smaller][mod][mask];
 
	int x = sL[index] - '0';
	int y = sR[index] - '0';
	uint64_t res = 0;
	for (int i = 0; i < 10; ++i) {
		if (x > i && !larger)
			continue;
		if (y < i && !smaller)
			continue;
		int nLarger = larger;
		int nSmaller = smaller;
		if (x < i)
			nLarger = 1;
		if (y > i)
			nSmaller = 1;
		int nRem[10];
		for (int j = 1; j < 10; ++j)
			nRem[j] = (rem[j] * 10 + i) % j;
		int nMod = GetMod(nRem);
		int nMask = (i > 0) ? (mask | (1 << (i - 1))) : mask;
		res += Count(index + 1, nLarger, nSmaller, nMod, nMask);
	}
 
	return dp[index][larger][smaller][mod][mask] = res;
}
 
void Solve() {
	uint64_t L, R;
	cin >> L >> R >> k;
	sL = to_string(L);
	sR = to_string(R);
	while (sL.size() < sR.size())
		sL = "0" + sL;
 
	for (int i = 0; i < sR.size(); ++i)
		for (int j = 0; j < TOTAL; ++j)
			for (int k = 0; k < 512; ++k)
				dp[i][0][0][j][k] = dp[i][1][0][j][k] = dp[i][0][1][j][k] = dp[i][1][1][j][k] = -1;
 
	uint64_t result = Count(0, 0, 0, 0, 0);
	cout << result << endl;
}
 
int main() {
	int q;
	cin >> q;
	for (int i = 0; i < q; ++i)
		Solve();
} 