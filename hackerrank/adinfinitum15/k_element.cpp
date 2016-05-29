#include <iostream>

using namespace std;
typedef long long int64;
const int MAX_N = 2 * 1e6;
const int64 MOD = 1e9 + 7;

int64 power(int a, int64 b)
{
	if (b == 0)
		return 1;
	int64 res = power(a, b >> 1);
	res = res * res % MOD;
	if (b & 1)
		res = res * a % MOD;
	return res;
}

inline int64 inverse(int a)
{
	return power(a, MOD - 2);
}

int main()
{
	int fact[MAX_N + 1];
	fact[0] = 1;
	for (int i = 1; i <= MAX_N; i++)
		fact[i] = (int)((int64)fact[i - 1] * i % MOD);

	int t;
	cin >> t;
	int k, n;
	for (int tt = 0; tt < t; tt++) {
		cin >> n >> k;
		n--;
		k--;
		int64 res = fact[n] * inverse(fact[k]) % MOD;
		res = res * inverse(fact[n - k]) % MOD;	
		cout << res << endl;
	}
}