#include <iostream>

using namespace std;
typedef unsigned long long uint64;

uint64 get_xor(uint64 n)
{
	if (n == 0)
		return 0;

	if (n & 1) {
		uint64 cnt = ((n - 1) >> 1) + 1;
		return (cnt & 1) ? 1 : 0;
	} else {
		uint64 cnt = ((n - 2) >> 1) + 1;
		uint64 res = (cnt & 1) ? 1 : 0;
		return res ^ n;
	}
}

int main()
{
	int q;
	cin >> q;
	for (int i = 0; i < q; i++) {
		uint64 l, r;
		cin >> l >> r;
		cout << (get_xor(l - 1) ^ get_xor(r)) << endl;
	}
}