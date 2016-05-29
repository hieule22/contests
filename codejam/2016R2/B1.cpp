#include <cstdio>

int n, k;
double p[200];
double dp[200][200][200];

double solve()
{
	scanf("%d %d", &n, &k);
	for (int i = 0; i < n; i++)
		scanf("%f", p + i);
	for (int i = 0; i < n; i++) 
		for (int j = 0; j <= k)
}

int main()
{
	int t;
	scanf("%d", &t);
	for (int tt = 1; tt <= t; tt++)
		printf("Case #%d: %.6f\n", solve());
}