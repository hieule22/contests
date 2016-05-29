#include <iostream>
#include <cstdio>
#include <climits>
#include <algorithm>

using namespace std;

double v, tp;
int n;

int main()
{
	cin >> v >> tp;
	cin >> n;

	double x, t, D = INT_MAX;
	int ans = 0;
	for (int i = 0; i < n; i++) {
		cin >> x >> t;
		double total = (t + tp) * v + x;
		if (D > total) {
			D = total;
			ans = i + 1;
		}
	}

	printf("%.5f %d\n", D, ans);
}