#include <iostream>

using namespace std;

typedef unsigned long long uint64;

const int MOD = 1000 * 1000 * 1000 + 7;
const int MAX = 5000;

int pascal[MAX + 1][MAX + 1];
uint64 res[MAX + 1];

uint64 power(int base, int exponent) {
	if (exponent == 0)
		return 1;
	uint64 res = power(base, exponent >> 1);
	res = (res * res) % MOD;
	if (exponent & 1)
		res = (res * base) % MOD;
	return res;
}

int main()
{
	ios_base::sync_with_stdio(false);

	for (int i = 0; i < MAX + 1; i++) {
		res[i] = 0;
		for (int j = 0; j < MAX + 1; j++)
			pascal[i][j] = 0;
	}

	for (int i = 0; i < MAX + 1; i++) {
		pascal[i][0] = 1;
		for (int j = 1; j < i + 1; j++) {
			pascal[i][j] = (pascal[i - 1][j] + pascal[i - 1][j - 1]) % MOD;
		}
	}

	for (int n = 1; n < MAX + 1; n++) {
		for (int i = 1; i <= n; i++)
			res[n] = (res[n] + power(25, i - 1) * power(pascal[n][i], 2) *
				26) % MOD;
	}

	int t, n;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		cin >> n;
		cout << res[n] << endl;
	}
}