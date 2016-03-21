#include <stdio.h>
#include <algorithm>
#include <climits>
using namespace std;

const int MAX = 1005;
const char living = '*';
int n, m;
char line[MAX];

int main()
{
	int t;
	scanf("%d", &t);
	int top, down, left, right;
	for (int tt = 0; tt < t; tt++)
	{
		scanf("%d %d", &n, &m);
		top = n - 1;
		down = 0;
		left = m - 1;
		right = 0;

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			scanf("%s", line);
			for (int j = 0; j < m; j++) {
				if (line[j] == living) {
					cnt++;
					top = min(top, i);
					down = max(down, i);
					left = min(left, j);
					right = max(right, j);
				}
			}
		}

		if (cnt == 0) {
			printf("0\n");
			continue;
		}

		int res = INT_MAX, cur;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cur = max(i - top, down - i);
				cur = max(cur, j - left);
				cur = max(cur, right - j);
				cur++;
				res = min(res, cur);
			}
		}

		printf("%d\n", res);
	}
}