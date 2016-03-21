#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);	
	int t;
	cin >> t;
	while (t--)
	{
		int n, m, z, l, r, b;
		cin >> n >> m >> z >> l >> r >> b;

		if (m == 1) {
			cout << min(n, z + l + r + b) << endl;
			continue;
		}

		int total = n * m;
		int res = 0;
		int rows = min(b, n);
		rows = min(rows, (l + r) / (m - 1));
		res += rows * m;
		n -= rows;
		b -= rows;
		l -= rows * (m - 1);

		if (b == 0) {
			cout << res + min(total - res, l + r + z) << endl;
			continue;
		}

		if (n == 0) {
			cout << res << endl;
			continue;
		}

		res += (l + r);
		int remain = m - (l + r);
		int decrease = min(b, (remain + 1) / 2);
		res += decrease;
		b -= decrease;
		remain -= decrease;
		res += min(z, remain);
		z -= min(z, remain);
		n--;

		decrease = min(b, n * ((m + 1) / 2)); 
		res += decrease;
		res += min(m * n - decrease, z);
		cout << res << endl;
	}
}