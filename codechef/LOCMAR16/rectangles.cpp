#include <stdio.h>

const int MAX_SIZE = 505;
int a[MAX_SIZE][MAX_SIZE];
int n, m;

int main()
{
	int t;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++)
	{
		scanf("%d %d", &n, &m);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				scanf("%d", &a[i][j]);

		for (int col = 0; col < m; col++)
		{
			int r = 0, c = col;
			while (r < n && c >= 0) {
				printf("%d ", a[r][c]);
				r++;
				c--;
			}
		}

		for (int row = 1; row < n; row++) 
		{
			int r = row, c = m - 1;
			while (r < n && c >= 0) {
				printf("%d ", a[r][c]);
				r++;
				c--;
			}
		}
		printf("\n");
	}
}