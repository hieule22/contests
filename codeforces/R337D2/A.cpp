#include <stdio.h>

int n;

int main()
{
	scanf("%d", &n);

	int ways = 0;
	if (n % 2 == 0) {
		ways = n / 2;
		ways >>= 1;
		if (n % 4 == 0)
			ways--;
	}

	printf("%d\n", ways);
}