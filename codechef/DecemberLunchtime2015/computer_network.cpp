#include <iostream>
using namespace std;

int main()
{
	int t, n, m;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		cin >> n >> m;
		if (n != m) {
			cout << "-1 -1" << endl;
		} else {
			for (int i = 1; i < n; i++) {
				cout << i << " " << (i + 1) << endl;
			}
			cout << n << " 1" << endl;
		}
	}
}