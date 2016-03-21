#include <stdio.h>

int **recurse(int n)
{
	if (n == 0) { 
		int **res = new int*[1];
		res[0] = new int[1];
		res[0][0] = 1;
		return res;
	}
	int **ans = recurse(n - 1);
	int **res = new int*[1 << n];
	for (int i = 0; i < (1 << n); i++) {
		res[i] = new int[1 << n];
		for (int j = 0; j < (1 << (n - 1)); j++) {
			res[i][j] = ans[i >> 1][j];
		}
		for (int j = (1 << (n - 1)); j < (1 << n); j++) {
			if (i & 1) res[i][j] = res[i][j - (1 << (n - 1))];
			else res[i][j] = -res[i][j - (1 << (n - 1))];
		}
	}
	delete[] ans;
	return res;
}

int main()
{
	int n;
	scanf("%d", &n);
	int **a = recurse(n);
	for (int i = 0; i < (1 << n); i++) {
		for (int j = 0; j < (1 << n); j++) {
			if (a[i][j] == 1) printf("+");
			else printf("*");
		}
		printf("\n");	
	}
}