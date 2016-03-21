#include <iostream>
using namespace std;

typedef unsigned long long uint64;
const int MOD = 1000 * 1000 * 1000 + 7;

int main()
{
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	uint64 dp[n + 1], a[n + 1], power[n];
	for (int i = 1; i <= n; i++) 
		cin >> a[i];
	power[0] = 1;
	for (int i = 1; i < n; i++) {
		power[i] = 2 * power[i - 1];
		power[i] %= MOD;
	}
	dp[0] = 1;
	for (int i = 1; i <= n; i++) {
		dp[i] = (2 * dp[i - 1] + power[i - 1]) % MOD;
	}

	uint64 total = 0, sum;
	for (int i = 1; i <= n; i++) {
		sum = (dp[i - 1] * a[i]) % MOD;
		sum = (sum * power[n - i]) % MOD;
		total = (total + sum) % MOD;
	}

	cout << total << endl;
}