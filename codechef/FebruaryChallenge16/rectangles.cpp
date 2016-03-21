#include <stdio.h>

typedef unsigned long long uint64;

double choose(uint64 n, int p)
{
	double result = 1;
	for (uint64 i = n; i > n - p; i--)
		result *= i;
	for (int i = 1; i <= p; i++)
		result /= i;
	return result;
}

int main()
{
	int t;
	scanf("%d", &t);

	for (int tt = 0; tt < t; tt++) {
		uint64 n, m;
		int k;
		scanf("%lld %lld %d", &n, &m, &k);

		double total = choose(n + 1, 2) * choose(m + 1, 2);

		double sum = 0;
		uint64 num, row, col;
		for (int i = 0; i < k; i++) {
			scanf("%lld", &num);
			row = ((num - 1) / m) + 1;
			col = num - (row - 1) * m;
			
			sum += 1.0 * row * (n + 1 - row) * col * (m + 1 - col);
		}

		double result = sum / total;

		printf("%.7f\n", result);
	}
}