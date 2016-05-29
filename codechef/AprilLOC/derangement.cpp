#include <stdio.h>
typedef unsigned long long uint64;

const uint64 MOD = 1e9 + 7;
const int MAX_N = 1e5 + 5;
uint64 dng[MAX_N];

int main()
{
	dng[1] = 0;
	dng[2] = 1;
	for (int i = 3; i < MAX_N; i++)
		dng[i] = (i - 1) * (dng[i - 1] + dng[i - 2]) % MOD;
	int t, n;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		scanf("%d", &n);
		printf("%lld\n", dng[n]);
	}
}