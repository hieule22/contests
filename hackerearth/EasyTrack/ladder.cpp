#include <iostream>
using namespace std;

const int MOD = 1000000007;

int main()
{
	ios_base::sync_with_stdio(false);
	int t, n;
	cin >> t >> n;
	long long dp[n + 1];
	dp[0] = 1;
	for (int i = 1; i <= n; i++) {
		dp[i] = 0;
		if (i >= 2) 
			dp[i] += dp[i - 2];
		if (i >= 5)
			dp[i] += dp[i - 5];
		dp[i] %= MOD;
	}

	int cur, k;
	for (int tt = 0; tt < t; tt++) {
		cin >> cur >> k;
		if (k == 5) {
			cout << dp[cur] << endl;
			continue;
		}
		long long steps[cur + 1];
		for (int i = 0; i <= cur; i++) {
			steps[i] = dp[i];
			if (i >= k)
				steps[i] += steps[i - k];
			steps[i] %= MOD;
		}

		cout << steps[cur] << endl;
	}
}