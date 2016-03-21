#include <iostream>
#include <cstdarg>
#include <climits>
#include <algorithm>

using namespace std;

int main()
{
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++)
	{
		int N;
		cin >> N;
		int amount[2][4];
		for (int i = 0; i < 4; i++)
			cin >> amount[0][i];

		int m, n, cost;
		m = 0;
		for (int i = 1; i < N; i++) {
			m = (i & 1);
			n = 1 - m;

			for (int j = 0; j < 4; j++) {
				cin >> cost;
				int result = INT_MAX;
				for (int k = 0; k < 4; k++) {
					if (k != j) result = min(result, amount[n][k]);
				}
				amount[m][j] = result + cost;
			}
		}

		int result = INT_MAX;
		for (int i = 0; i < 4; i++)
			result = min(result, amount[m][i]);

		cout << result << endl;
	}
}