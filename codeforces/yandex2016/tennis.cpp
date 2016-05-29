#include <cstdio>
#include <vector>

int n;
double pr[10][10];
double res;
int arr[10];

inline void swap(int x, int y) {
	int temp = arr[x];
	arr[x] = arr[y];
	arr[y] = temp;
}

void process()
{
	double ans = 1;
	for (int i = 0; i < n; i++)
		for (int j = i + 1; j < n; j++)
			ans *= pr[arr[i]][arr[j]];
	res += ans;
}

void permute(int k)
{
	int i;
	if (k == 0)
		process();
	else {
		for (int i = k - 1; i >= 0; i--) {
			swap(i, k - 1);
			permute(k - 1);
			swap(i, k - 1);
		}
	}
}

int main()
{
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++) {
			scanf("%lf", &pr[i][j]);
		}

	for (int i = 0; i < n; i++)
		arr[i] = i;

	res = 0;
	permute(n);
	printf("%.9lf\n", res);
}