#include <iostream>
#define min(a, b) ((a < b) ? a : b)
#define max(a, b) ((a > b) ? a : b)

using namespace std;

typedef unsigned long long uint64;

int main() {
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	while (t--) {
		uint64 a, b, m;
		cin >> a >> b >> m;
		uint64 sum = (m + 1) * m / 2;
		uint64 ans;
		if (sum >= min(a, b))
			ans = max(a, b) - min(a, b);
		else
			ans = (a - sum) + (b - sum);
		cout << ans << endl;
	}
}