#include <stdio.h>
#include <stdlib.h>
#include <time.h>

const int MAX = 1000;
int n = MAX;
int m = MAX;
int q = 50;

int main()
{
	srand(time(NULL));
	printf("%d %d\n", n, m);
	int num;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			num = rand() % MAX + 1;
			printf("%d ", num);
		}
		printf("\n");
	}

	printf("%d\n", q);
	int a, b;
	for (int i = 0; i < q; i++) {
		// a = rand() % MAX + 1;
		// b = rand() % MAX + 1;
		// a = b = 1;
		a = 1;
		b = i + 1;
		printf("%d %d\n", a, b);
	}
}
