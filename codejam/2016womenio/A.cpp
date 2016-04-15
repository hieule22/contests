#include <stdio.h>
#include <iostream>
// #define TEST

using namespace std;

void solve(int testNumber)
{
	int n;
	cin >> n;
	int price[2 * n], known[2 * n];
	for (int i = 0; i < 2 * n; i++) {
		cin >> price[i];
		known[i] = 0;
	}

	cout << "Case #" << testNumber << ":";
	for (int i = 0; i < 2 * n; i++) {
		if (!known[i]) {
			cout << " " << price[i];
			int target = price[i] * 4 / 3;
			int low = i + 1, high = 2 * n - 1, mid;
			while (low < high) {
				mid = low + ((high - low) >> 1);
				if (price[mid] < target)
					low = mid + 1;
				else
					high = mid;
			}
			while (known[low])
				low++;
			known[low] = 1;
		}
	}
	cout << endl;
}

int main()
{
	#ifndef TEST
	freopen("A-small-practice.in.txt", "r", stdin);
	freopen("A-small-practice.out", "w", stdout);
	#endif
	ios_base::sync_with_stdio(0);

	int t;
	cin >> t;
	for (int i = 1; i <= t; i++)
		solve(i);
}
