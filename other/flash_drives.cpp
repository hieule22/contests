#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n, m;
	cin >> n >> m;
	int a[n];
	for (int i = 0; i < n; i++)
		cin >> a[i];

	sort(a, a + n);
	int cur = 0;
	int i;
	for (i = n - 1; i >= 0; i--) {
		cur += a[i];
		if (cur >= m) break;
	}
	cout << n - i << endl;
}