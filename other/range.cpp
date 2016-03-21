#include <iostream>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	int a[n];
	int len, res = 0;
	for (int i = 0; i < n; i++) {
		cin >> a[i];
		len = 1;
		for (int j = i - 1; j >= 0; j--) {
			if (abs(a[j] - a[i]) > 1) break;
			len++;
		}
		res = max(res, len);
	}
	cout << res << endl;
}