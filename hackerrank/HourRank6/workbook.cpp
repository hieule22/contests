#include <iostream>

using namespace std;

int main()
{
	int n, k;
	cin >> n >> k;

	int res = 0, cur = 1, cnt;
	for (int i = 0; i < n; i++) {
		cin >> cnt;
		int j;
		for (j = cnt; j >= k; j -= k) {
			if (cnt - j + 1 <= cur && cur <= cnt - (j - k + 1) + 1) {
				res++;
				// cout << cur << endl;
			}
			cur++;
		}
		if (cnt - j + 1 <= cur && cur <= cnt) {
			// cout << cur << endl;
			res++;
		}
		if (j > 0)
			cur++;
	}
	cout << res << endl;
}