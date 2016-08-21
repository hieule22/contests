#include <stdio.h>
#include <vector>
#include <algorithm>
using namespace std;
typedef unsigned long long uint64;

const int MAX = 100005;
int n, k, m, a[MAX];

int main()
{
	int t;
	scanf("%d", &t);

	for (int tt = 0; tt < t; tt++)
	{
		scanf("%d %d %d", &n, &k, &m);
		for (int i = 0; i < n; i++)
			scanf("%d", a + i);

		vector<int> delta;
		int actual;
		for (int i = 0; i < n; i++) {
			scanf("%d", &actual);
			if (actual < a[i])
				delta.push_back(a[i] - actual);
		}

		vector<int> buttons;
		int time;
		for (int i = 0; i < k + m; i++) {
			scanf("%d", &time);
			buttons.push_back(time);
		}

		sort(delta.begin(), delta.end());
		sort(buttons.begin(), buttons.end());

		int p1 = delta.size() - 1, p2 = buttons.size() - 1;
		uint64 res = 0;
		for (; p1 >= 0; p1--) {
			while (p2 >= 0 && buttons[p2] > delta[p1])
				p2--;

			if (p2 >= 0) {
				res += delta[p1] - buttons[p2];
				p2--;
			}
			else
				break;
		}

		for (; p1 >= 0; p1--)
			res += delta[p1];

		printf("%lld\n", res);

	}
}