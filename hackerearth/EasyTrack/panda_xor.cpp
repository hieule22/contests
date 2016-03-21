#include <iostream>
#include <string.h>

using namespace std;
typedef unsigned long long uint64;
const uint64 MOD = 1000000007;

int main()
{
	int n, num;
	cin >> n;
	uint64 cnt[128], update[128];
	for (int i = 0; i < 128; i++) {
		cnt[i] = 0;
		update[i] = 0;
	}

	for (int i = 0; i < n; i++) {
		cin >> num;
		for (int j = 0; j < 128; j++) {
			if (cnt[j] > 0) {
				update[j ^ num] += cnt[j];
				update[j ^ num] %= MOD;
			}
		}
		update[num]++;
		for (int j = 0; j < 128; j++)
			cnt[j] = update[j];

	}

	uint64 total = 0;
	for (int i = 0; i < 128; i++) {
		if (cnt[i]) {
			// cout << i << " " << cnt[i] << endl;
			total += cnt[i] * (cnt[i] - 1) / 2;
			total %= MOD;
		}
	}

	cout << total << endl;
}
