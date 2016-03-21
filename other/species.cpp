#include <iostream>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	for (int i = 0; i < t; i++)
	{
		int n;
		cin >> n;
		int a[n], b[n];
		for (int j = 0; j < n; j++) cin >> a[j];
		for (int j = 0; j < n; j++) cin >> b[j];
		bool isOk = true;
		int cur = -1;
		for (int j = 0; j < n; j++)
		{
			if (cur > max(a[j], b[j])) {
				isOk = false;
				break;
			} else {
				if (a[j] >= cur && a[j] < b[j])
					cur = a[j];
				else
					cur = b[j];
			}
		}
		cout << ((isOk) ? "YES" : "NO") << endl;
	}
}