#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;
int n;

int binarySearch(const int * a, int index)
{
	int cnt = a[index - 1] - a[0];
	if (cnt > n / 4)
		return n + 1;

	int total = a[n];
	if (total == n / 4)
		return index - 1;

	int begin = index, end = n, mid;
	while (begin < end) {
		int mid = begin + (end - begin) / 2;
		cnt = total - (a[mid] - a[index - 1]);
		if (cnt <= n / 4)
			end = mid;
		else
			begin = mid + 1;
	}

	return begin;
}

int main()
{
	cin >> n;
	char s[n + 1];
	cin >> s;
	int a[n + 1], g[n + 1], c[n + 1], t[n + 1];
	a[0] = g[0] = c[0] = t[0];

	for (int i = 1; i <= n; i++)
	{
		char cur = s[i - 1];
		a[i] = a[i - 1];
		g[i] = g[i - 1];
		c[i] = c[i - 1];
		t[i] = t[i - 1];

		if (cur == 'A')
			a[i]++;
		else if (cur == 'G')
			g[i]++;
		else if (cur == 'T')
			t[i]++;
		else
			c[i]++;
	}

	int res = INT_MAX;
	for (int i = 1; i <= n; i++) {
		int j = max(binarySearch(a, i), binarySearch(g, i));
		j = max(j, binarySearch(t, i));
		j = max(j, binarySearch(c, i));
		if (j == n + 1)
			break;
		res = min(res, j - i + 1);
	}

	cout << res << endl;
}

