#include <iostream>
#include <algorithm>

using namespace std;

int main() 
{
	int n, m;
	cin >> n >> m;
	int a[n], b[m];
	for (int i = 0; i < n; i++) cin >> a[i];
	for (int i = 0; i < m; i++) cin >> b[i];

	sort(a, a + n);
	int low, high, mid;
	for (int i = 0; i < m; i++) 
	{
		if (b[i] < a[0]) {
			cout << "0 ";
			continue;
		}
		low = 0, high = n - 1;
		while (low != high) {
			mid = low + ((high - low + 1) >> 1);
			if (a[mid] > b[i])
				high = mid - 1;
			else
				low = mid;
		}
		cout << (low + 1) << " ";
	}
	cout << endl;
}