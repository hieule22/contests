#include <iostream>
#include <climits>
#include <algorithm>

using namespace std;

int main()
{
	int n;
	cin >> n;
	int c[n];
	for (int i = 0; i < n; i++) {
		cin >> c[i];
	}

	int dp[n][n];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (i < j) dp[i][j] = INT_MAX;
			else dp[i][j] = 1;
		}
	}

	for (int i = 2; i <= n; i++) {
		for (int j = 0; j <= n - i; j++) {
			dp[j][j + i - 1] = i;
			for (int k = 1; k < i; k++) {
				dp[j][j + i - 1] = min(dp[j][j + i - 1],
					dp[j][j + k - 1] + dp[j + k][j + i - 1]);
			}
			if (c[j] == c[j + i - 1])
				dp[j][j + i - 1] = min(dp[j][j + i - 1],
					dp[j + 1][j + i - 2]);
		}
	}
	cout << dp[0][n - 1] << endl;
}