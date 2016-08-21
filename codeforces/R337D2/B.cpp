#include <stdio.h>

const int INF = (int)1e9;
const int MAX_N = 200005;
int n, a[MAX_N];

int main()
{
	scanf("%d", &n);
	int min = INF;
	for (int i = 0; i < n; i++) {
		scanf("%d", a + i);
		min = (a[i] < min) ? a[i] : min;
	}

	int max_cnt = 0, begin, end, cnt, i;
	begin = end = 0;
	while (i < n && a[i] != min) {
		begin++;
		i++;
	}

	i = n - 1;
	while (i >= 0 && a[i] != min) {
		end++;
		i--;
	}

	max_cnt = begin + end;
	cnt = 0;
	for (i = 0; i < n; i++) {
		if (a[i] > min) {
			cnt++;
		} else {
			max_cnt = (cnt > max_cnt) ? cnt : max_cnt;
			cnt = 0;
		}
	}
	max_cnt = (cnt > max_cnt) ? cnt : max_cnt;

	long long res = (long long) min * n + max_cnt;
	printf("%I64d\n", res);
}