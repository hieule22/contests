#include <iostream>
#include <cstdint>
#include <unordered_map>
using namespace std;

uint64_t MOD = 1e9 + 7;
int m, n, p;
uint64_t cache[500001];


uint64_t Pow(int a, int b) {
	if (b == 0)
		return 1;
	uint64_t res = Pow(a, b >> 1);
	res = (res * res) % MOD;
	if (b & 1)
		res = (res * a) % MOD;
	return res;
}

void Solve() {
	uint64_t res = 0;
	uint64_t factor = 1;
	uint64_t a = 1, b = Pow(n, m - n), inverse = Pow(n, MOD - 2);
	for (int i = n; i <= m; ++i) {
		uint64_t temp = a * b % MOD;
		a = (a * (n - 1)) % MOD;
		b = (b * inverse) % MOD;
		temp = temp * factor % MOD;
		factor = (factor * i) % MOD;
		//factor = (factor * Pow(i - n + 1, MOD - 2)) % MOD;
		factor = (factor * cache[i - n + 1]) % MOD;
		res = (res + temp) % MOD;
	}
	cout << res << endl;
}

int main() {
	cin >> p;
	for (int i = 1; i <= 500000; ++i)
		cache[i] = Pow(i, MOD - 2);
	for (int i = 0; i < p; ++i) {
		cin >> m >> n;
		if (n == 1) {
			cout << 1 << endl;
			continue;
		}
		Solve();
	}
}