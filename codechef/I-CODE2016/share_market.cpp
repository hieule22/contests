#include <stdio.h>

int main()
{
	int t;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++) 
	{
		int n;
		scanf("%d", &n);

		int result = 0, current = 0;
		int L = 0, R = 0, begin = 0, end = 0, data;

		for (int i = 0; i < n; i++) {
			scanf("%d", &data);

			if (current + data <= 0) {
				current = 0;
				begin = end = i + 1;
			} else {
				current += data;
				end = i;
				if (result <= current) {
					result = current;
					L = begin;
					R = end;
				}
			}
		}

		printf("%d %d %d\n", L, R, result);
	}
}