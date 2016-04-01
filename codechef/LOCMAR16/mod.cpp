#include <stdio.h>
#include <algorithm>

typedef unsigned long long uint64;

int main()
{
	int n;
	scanf("%d", &n);
	uint64 a[n];
	for (int i = 0; i < n; i++)
		scanf("%lld", a + i);

	std::sort(a, a + n);
	if (n == 1) {
		printf("0\n");
		return 0;
	} 
	int index = n - 2;
	while (index >= 0 && a[index] == a[n - 1])
		index--;
	if (index < 0) 
		printf("0\n");
	else
		printf("%lld\n", a[index]);
}