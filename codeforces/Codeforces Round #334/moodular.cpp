#include <iostream>

using namespace std;
typedef unsigned long long uint64;

const int MOD = 1000 * 1000 * 1000 + 7;

uint64 mod_power(int base, int expo)
{
	if (expo == 0) return 1;
	uint64 res = mod_power(base, expo >> 1);
	res = (res * res) % MOD;
	if (expo & 1) res = (res * base) % MOD;
	return res;
}

int main()
{
	ios_base::sync_with_stdio(false);
	int p, k;
	cin >> p >> k;

	uint64 res;
	if (k == 0) {
		res = mod_power(p, p - 1);
	} else {
		int period = 1;
		uint64 cur = k;
		while (cur != 1) {
			period++;
			cur = (cur * k) % p;
		}
		int sequence = (p - 1) / period;
		res = (k == 1) ? p : 1;
		res = (res * mod_power(p, sequence)) % MOD;
	}
	cout << res << endl;
}