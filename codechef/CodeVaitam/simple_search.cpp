#include <iostream>
#include <algorithm>

using namespace std;

const int MAX = (int)1e6;
double a[MAX + 1];

int main()
{
	ios_base::sync_with_stdio(false);

	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++)
	{
		int n, q;
		cin >> n >> q;
		for (int i = 0; i < n; i++)
			cin >> a[i];
		sort(a, a + n);

		double num;
		for (int i = 0; i < q; i++) {
			cin >> num;
			int res;
			if (num >= a[n - 1]) {
				res = 0;
			} else {
				int left = 0, right = n - 1, mid;
				while (left < right) {
					mid = left + ((right - left) >> 1);
					if (a[mid] <= num)
						left = mid + 1;
					else
						right = mid;
				}
				res = n - left;
			}

			cout << res << endl;
		}
	}
}