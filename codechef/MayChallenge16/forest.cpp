#include <iostream>

using namespace std;
typedef unsigned long long uint64;
int N;
uint64 W, L;
uint64 heights[100000];
uint64 rates[100000];

int main()
{
	ios_base::sync_with_stdio(0);
	cin >> N >> W >> L;
	for (int i = 0; i < N; i++)
		cin >> heights[i] >> rates[i];
	uint64 low = 0, high = 1e18, mid;
	while (low < high) {
		mid = low + ((high - low) >> 1);
		uint total = 0, previous;
		for (int i = 0; i < N; i++) {
			uint64 height = heights[i] + mid * rates[i];
			if (height < heights[i] || height < mid * rates[i]) {
				total = 1e18;
				break;
			} else if (height >= L) {
				previous = total + height;
				if (previous < total) {
					total = 1e18;
					break;
				}
				total = previous;
			}
		}

		if (total >= W)
			high = mid;
		else
			low = mid + 1;
	}
	cout << low << endl;
}