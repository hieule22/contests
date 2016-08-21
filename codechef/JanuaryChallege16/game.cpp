#include <stdio.h>

typedef unsigned long long uint64;

const uint64 MOD = 1000 * 1000 * 1000 + 7;

uint64 power(int a, int base);

int main()
{
	int t, n;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++)
	{
		scanf("%d", &n);
		uint64 sum = 0, a, prefix, temp;
		scanf("%lld", &a);
		prefix = (a * 2) % MOD;
		uint64 small_power = 2;

		for (int i = 1; i < n + 1; i++) {
			scanf("%lld", &a);
			temp = (a * prefix) % MOD;
			temp = (temp * power(2, n - i)) % MOD;
			sum = (sum + temp) % MOD;
			temp = (a * small_power) % MOD;
			prefix = (prefix + temp) % MOD;
			small_power = (small_power * 2) % MOD;
		}

		printf("%lld\n", sum);
	}
}

uint64 power(int a, int base)
{
	if (a == 0) 
		return 0;
	if (base == 0)
		return 1;
	uint64 res = power(a, base >> 1);
	res = (res * res) % MOD;
	if (base & 1)
		res = (res * a) % MOD;
	return res;
}