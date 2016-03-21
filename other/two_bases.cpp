#include <iostream>

using namespace std;

typedef unsigned long long uint64;

uint64 power(int n, int exponent) {
	if (exponent == 0) return 1;
	uint64 res = power(n, exponent >> 1);
	res *= res;
	if (exponent & 1) res *= n;
	return res;
}

int main()
{
	int n, base, digit;
	uint64 x, y, magnitude;

	cin >> n >> base;
	x = 0;
	magnitude = power(base, n - 1);
	for (int i = 0; i < n; i++) {
		cin >> digit;
		x += digit * magnitude;
		magnitude /= base;
	}

	cin >> n >> base;
	y = 0;
	magnitude = power(base, n - 1);
	for (int i = 0; i < n; i++) {
		cin >> digit;
		y += digit * magnitude;
		magnitude /= base;
	}

	if (x != y)
		cout << ((x < y) ? "<" : ">") << endl;
	else
		cout << "=" << endl;
}