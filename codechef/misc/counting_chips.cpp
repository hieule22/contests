#include <iostream>
using namespace std;

int n, value[105];

int main()
{
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		cin >> n;
		int sum = 0;
		for (int i = 0; i < n; i++) {
			cin >> value[i];
			sum += value[i];
		}

		bool subset[sum + 1][n + 1];

		for (int i = 0; i <= n; i++)
			subset[0][i] = true;

		for (int i = 1; i <= sum; i++)
			subset[i][0] = false;

		for (int i = 1; i <= sum; i++) {
			for (int j = 1; j <= n; j++) {
				subset[i][j] = subset[i][j - 1];
				if (i >= value[j - 1])
					subset[i][j] = subset[i][j] || subset[i - value[j - 1]][j - 1];
			}
		}

		int delta;
		for (int i = (sum >> 1); i >= 0; i--) {
			if (subset[i][n]) {
				delta = abs(sum - 2 * i);
				break;
			}
		}

		cout << delta << endl;
	}
}