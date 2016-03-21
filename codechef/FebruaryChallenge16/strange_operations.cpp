#include <iostream>

using namespace std;

typedef unsigned long long uint64;
const uint64 MOD = (uint64)1e9 + 7;
const int MAX = (int)1e5;

uint64 mod_power(int base, int exponent)
{
	if (exponent == 0)
		return 1;
	uint64 result = mod_power(base, exponent >> 1);
	result = result * result % MOD;
	if (exponent & 1)
		result = result * base % MOD;
	return result;
}

uint64 mod_inverse(int n)
{
	return mod_power(n, MOD - 2);
}

int main()
{
	ios_base::sync_with_stdio(false);
	
	int t, n, x;
	uint64 m;
	uint64 a[MAX + 1];

	cin >> t;
	for (int tt = 0; tt < t; tt++)
	{
		cin >> n >> x >> m;
		m %= MOD;
		for (int i = 1; i <= n; i++) {
			cin >> a[i];
			a[i] %= MOD;
		}

		uint64 result = a[x];
		uint term = 1, temp;
		for (int i = 1; i < x; i++) {
			term = term * (m + i - 1) % MOD;
			term = term * mod_inverse(i) % MOD;
			temp = term * a[x - i] % MOD;
			result = (result + temp) % MOD;
		}

		cout << result << endl;
	}
}