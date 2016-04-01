#include <stdio.h>
#include <algorithm>

int main()
{
	int n;
	scanf("%d", &n);
	int a[n];
	for (int i = 0; i < n; i++)
		scanf("%d", a + i);
	std::sort(a, a + n);

	int t, low, high;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++)
	{
		scanf("%d %d", &low, &high);
		auto left = std::lower_bound(a, a + n, low);
		auto right = std::upper_bound(a, a + n, high);
		int cnt = right - left;
		if (cnt > 0)
			printf("%d\n", cnt);
		else
			printf("0\n");
	}
}