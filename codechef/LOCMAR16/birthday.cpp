#include <iostream>

using namespace std;

const int MAX_N = 1e5 + 5;
int a[MAX_N];

int main()
{
	int t;
	cin >> t;

	for (int tt = 0; tt < t; tt++)
	{
		int n;
		cin >> n;
		long long total = 0;
		for (int i = 0; i < n; i++) {
			cin >> a[i];
			total += a[i];
		}

		if (total % n != 0) {
			cout << "No Treat" << endl;
			continue;
		} else {
			long long moves = 0;
			long long average = total / n;
			for (int i = 0; i < n; i++) {
				moves += ((average > a[i]) ? (average - a[i]) : (a[i] - average));
			}
			cout << (moves / 2) << endl;
		}
	}
}