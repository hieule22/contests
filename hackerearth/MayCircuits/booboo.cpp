#include <iostream>
#include <algorithm>
#include <cstdint>
#include <climits>

using namespace std;
	
int n, m;
uint64_t q[100005];

bool check(uint64_t total)
{	
	uint64_t cur = 0, cnt = 0;
	for (int i = 0; i < n; i++) {
		if (q[i] > total || cnt > m)
			return false;
		if (cur + q[i] > total) {
			cnt++;
			cur = q[i];
		} else {
			cur += q[i];
		}
	}
	cnt++;
	return (cnt <= m);
}


int main()
{
	cin >> n >> m;
	for (int i = 0; i < n; i++)
		cin >> q[i];

	uint64_t low = 1, high = ULLONG_MAX;
	while (low < high) {
		uint64_t mid = low + ((high - low) >> 1);
		if (check(mid))
			high = mid;
		else
			low = mid + 1;
	}

	cout << low << endl;
}