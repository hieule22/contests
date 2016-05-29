#include <iostream>

using namespace std;

int n, a[10000], b[10000];
void solve()
{
	cin >> n;
	for (int i = 0; i < n; i++)
		cin >> a[i];
	for (int i = 0; i < n; i++)
		cin >> b[i];
	int begin = 0, result = 0;
	for (int i = 0; i < n; i++) {
		if (b[i] <= (a[i] - begin))
			result++;
		begin = a[i];
	}
	cout << result << endl;
}

int main()
{
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++)
		solve();
}