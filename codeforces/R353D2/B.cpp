#include <iostream>
#include <algorithm>
#include <cstdint>
using namespace std;

bool check(int a, int n)
{
	return a >= 1 && a <= n;
}

int main()
{
	int n, a, b, c, d;
	cin >> n >> a >> b >> c >> d;

	uint64_t ans = 0;
	for (int i = 1; i <= n; i++) {
		int maxSum = min(a + b + i + n, a + c + i + n);
		maxSum = min(maxSum, c + d + i + n);
		maxSum = min(maxSum, b + d + i + n);

		int minSum = max(a + b + i + 1, a + c + i + 1);
		minSum = max(minSum, c + d + i + 1);
		minSum = max(minSum, b + d + i + 1);

		if (minSum <= maxSum)
			ans += (maxSum - minSum + 1);
	}

	cout << ans << endl;
}