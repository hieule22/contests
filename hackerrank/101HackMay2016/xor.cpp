#include <iostream>
#include <algorithm>
#include <climits>
using namespace std;

int compute(int n)
{
	int res = 0, cur = 1;
	while (cur <= n) {
		res++;
		cur <<= 1;
	}
	return res - 1;
}

int solve(int * a, int begin, int end)
{
	sort(a + begin, a + end + 1);
	int count = compute(a[end]);
	if (count == -1)
		return 0;

	int low = end;
	while (low >= begin && compute(a[low]) == count)
		low--;
	low++;

	if (low == begin) {
		for (int i = begin; i <= end; i++)
			a[i] -= (1 << count);
		return solve(a, begin, end);
	}

	int res = INT_MAX;
	for (int i = begin; i < low; i++)
		for (int j = low; j < end + 1; j++)
			res = min(res, a[i] ^ a[j]);
	return res;
}

int main()
{
	int n;
	cin >> n;
	int a[n];

	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}

	cout << solve(a, 0, n - 1) << endl;
}

