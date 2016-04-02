#include <stdio.h>
#define min(a, b) (a < b ? a : b)

typedef unsigned long long uint;

int main()
{
	uint t, r, g, b, k;
	scanf("%lld", &t);

	for (uint i = 0; i < t; i++)
	{
		scanf("%lld%lld%lld%lld", &r, &g, &b, &k);
		uint result = min(r, k - 1) + min(g, k - 1) + min(b, k - 1) + 1;
		printf("%lld\n", result);
	}
}