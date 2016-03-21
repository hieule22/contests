#include <stdio.h>
#include <algorithm>

using namespace std;

const int INF = 1000 * 1000 * 1000 + 7;

int dp[107][107];
bool solved[107][107];
int a[107];
int n;

int solve(int l, int r)
{
	if (l > r) return 0;
	if (solved[l][r]) return dp[l][r];
	solved[l][r] = true;
	int morel = 0, lessl = 0, morer = 0, lessr = 0;
	for (int i = 1; i < l; i++) {
		if (a[i] > a[l]) morel++;
		if (a[i] < a[l]) lessl++;
		if (a[i] > a[r]) morer++;
		if (a[i] < a[r]) lessr++;
	}
	for (int i = r + 1; i <= n; i++)
	{
		if (a[i] > a[l]) morel++;
		if (a[i] < a[l]) lessl++;
		if (a[i] > a[r]) morer++;
		if (a[i] < a[r]) lessr++;
	}
	dp[l][r] = min(dp[l][r], solve(l + 1, r) + min(morel, lessl));
	dp[l][r] = min(dp[l][r], solve(l, r - 1) + min(morer, lessr));
	return dp[l][r];
}


int main()
{
	int t;
	scanf("%d", &t);
	for (int tt = 1; tt <= t; tt++)
	{
		for (int i = 0; i < 107; i++)
		{
			for (int j = 0; j < 107; j++)
			{
				dp[i][j] = INF;
				solved[i][j] = false;
			}
		}
		scanf("%d", &n);
		for (int i = 1; i <= n; i++)
		{
			scanf("%d", &a[i]);
		}
		int ans = solve(1, n);
		printf("%d\n", ans);
	}
}