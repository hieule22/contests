#include <stdio.h>

typedef unsigned long long uint64;
uint64 MOD = 1e9 + 7;
uint64 n, k;

uint64 modPow(uint64 base, uint64 power)
{
	if (power == 0)
		return 1;
	uint64 result = modPow(base, power / 2);
	result = result * result % MOD;
	if (power % 2 == 1)
		result = result * base % MOD;
	return result;
}

int main()
{
	int t;
	scanf("%d", &t);

	for (int i = 0; i < t; i++)
	{
		scanf("%lld %lld", &n, &k);
		uint64 result = k * modPow(k - 1, n - 1) % MOD;
		printf("%lld\n", result);
	}
}