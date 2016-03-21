#include <iostream>
using namespace std;

const int MAX_N = 1500;
const int MAX_STONES = 1000000;

int main()
{
	ios_base::sync_with_stdio(false);
	int s;
	cin >> s;
	int m, n, k;
	for (n = 1500; n >= 2; n--) {
		k = s / (n - 1);
		m = s - (n - 1) * k;
		if (1 <= k && k <= MAX_STONES && 
				2 <= m + n && m + n <= MAX_N) {
			cout << m + n << endl;
			for (int i = 0; i < m; i++)
				cout << 1 << " ";
			for (int i = 0; i < n; i++)
				cout << k << " ";
			cout << endl;
			break;
		}
	}
}