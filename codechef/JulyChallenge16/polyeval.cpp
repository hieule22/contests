#include <iostream>
#include <cstdint>
using namespace std;

const uint32_t MOD = 786433;
uint32_t n, q, a[250001];

void Solve(uint32_t x) {
	uint64_t res = a[n];
	for (int i = n - 1; i >= 0; --i) 
		res = (a[i] + res * x) % MOD;
	cout << res << endl;
}

int main() {
	cin >> n;
	for (int i = 0; i < n + 1; i++)
		cin >> a[i];
	cin >> q;
	uint32_t x;
	for (int i = 0; i < q; i++) {
		cin >> x;
		Solve(x);
	}
}