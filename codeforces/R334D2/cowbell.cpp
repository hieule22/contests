#include <algorithm>
#include <iostream>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n, k;
	cin >> n >> k;
	int s[n];
	for (int i = 0; i < n; i++)
		cin >> s[i];

	int pairs = max(n - k, 0);
	int res = s[n - 1];
	int end = (pairs << 1);
	for (int i = 0; i < pairs; i++)
		res = max(res, s[i] + s[end - i - 1]);
	cout << res << endl;
}