#include <iostream>
#include <algorithm>

using namespace std;

typedef unsigned long long uint64;

int main() {
	ios_base::sync_with_stdio(false);
	int t;
	uint64 n, m, z, l, r, b;

	cin >> t;
	while (t--) {
		cin >> n >> m >> z >> l >> r >> b;
		uint64 total = n * m;

		if (m == 1) {
			cout << min(total, z + l + r + b) << endl;
			continue;
		}

		uint64 res = 0;

		// Fill L...L B R...R
		uint64 rows = min((r + l) / (m - 1), b);
		rows = min(rows, n);
		res += rows * m;
		n -= rows;
		b -= rows;
		uint64 decrease = min(rows * (m - 1), r);
		r -= decrease;
		l -= (rows * (m - 1) - decrease);

		if (n == 0) {
			cout << res << endl;
			continue;
		} else if (b == 0) {
			res += min(total - res, z + l + r);
			cout << res << endl;
			continue;
		}

		// Now l + r < m - 1 and n > 0 and b > 0
		// Fill L...L B Z B .. Z B R...R

		res += (l + r);
		uint64 remain = m - (l + r);
		decrease = min(b, (remain + 1) / 2);
		res += decrease;
		b -= decrease;
		// Put z in between
		res += min(remain - decrease, z);
		z -= min(remain - decrease, z);
		n--;

		// Fill B Z B .. Z B for the remaining rows
		decrease = min(b, n * ((m + 1) / 2));
		res += decrease;
		res += min(z, m * n - decrease);

		cout << res << endl;
	}
}