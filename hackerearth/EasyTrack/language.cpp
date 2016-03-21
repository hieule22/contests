#include <iostream>
#include <vector>
using namespace std;

const int N = 26;
const int MAX = 10000005;
const int MOD = 1000000007;

int main()
{
	ios_base::sync_with_stdio(0);
	vector<vector<int> > pred(N);
	int flag;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> flag;
			if (flag)
				pred[j].push_back(i);
		}
	}

	int dp[2][N];
	for (int i = 0; i < N; i++)
		dp[1][i] = 1;

	for (int length = 2; length < MAX; length++) {
		int a = (length % 2);
		int b = (a + 1) % 2;
		for (int i = 0; i < N; i++) {
			dp[a][i] = 0;
			for (int j : pred[i]) {
				dp[a][i] = (dp[a][i] + dp[b][j]) % MOD;
			}
		}
	}

	int q, len;
	char c;
	cin >> q;
	for (int i = 0; i < q; i++) {
		cin >> c >> len;
		cout << dp[len][c - 'A'] << endl;;
	}
}