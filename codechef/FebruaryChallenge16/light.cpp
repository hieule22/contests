#include <stdio.h>

int main()
{
	int t, n;
	scanf("%d", &t);

	int begin[] = {1, 2, 3, 4, 2, 3, 4};
	int end[] =   {2, 3, 4, 1, 5, 6, 7};

	for (int tt = 0; tt < t; tt++) {
		scanf("%d", &n);
		if (n < 7) {
			printf("-1\n");
			continue;
		}

		printf("%d\n", n);
		for (int i = 0; i < 7; i++)
			printf("%d %d\n", begin[i], end[i]);

		for (int i = 8; i <= n; i++)
			printf("2 %d\n", i);

		printf("1\n");
	}
}