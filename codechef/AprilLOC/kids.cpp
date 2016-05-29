#include <stdio.h>
#define max(a, b) ((a > b) ? a : b)

int main()
{
	int t;
	scanf("%d", &t);

	int n, first, second, cur;
	for (int tt = 0; tt < t; tt++) {
		scanf("%d", &n);
		first = second = -1;
		int result = 1, count = 0;
		for (int i = 0; i < n; i++) {
			scanf("%d", &cur);
			if (cur == first + second) {
				if (count < 3)
					count = 3;
				else
					count++;
			} else {
				result = max(result, count);
				count = 0;
			}
			first = second;
			second = cur;
		}
		result = max(result, count);
		printf("%d\n", result);
	}
}