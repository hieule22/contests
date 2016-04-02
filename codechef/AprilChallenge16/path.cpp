#include <stdio.h>

typedef unsigned long long uint64;
typedef unsigned int uint;

int main()
{
	uint t;
	scanf("%d", &t);
	uint64 n, m;
	bool possible;

	for (uint i = 0; i < t; i++)
	{
		scanf("%lld %lld", &n, &m);
		if (n == 1 || m == 1) {
			if (n == 2 || m == 2)
				possible = true;
			else
				possible = false;
		} else if (n % 2 == 0 || m % 2 == 0) {
			possible = true;
		} else {
			possible = false;
		}

		if (possible)
			printf("Yes\n");
		else
			printf("No\n");
	}	

}