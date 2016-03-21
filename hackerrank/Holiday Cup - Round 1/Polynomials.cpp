#include <iostream>
#define MAX 10
using namespace std;


typedef long long int64;

int64 pow(int base, int expo) {
	if (base == 0) 
		return 0;
	if (expo == 0 || base == 1) 
		return 1;
	int64 res = pow(base, expo >> 1);
	res *= res;
	if ((expo & 1) == 1) res *= base;
	return res;
}

int cmb[MAX][MAX];

int main() {
	ios_base::sync_with_stdio(false);

	for (int i = 0; i < MAX; i++) {
		cmb[i][0] = 1;
		for (int j = 1; j <= i; j++) {
			cmb[i][j] = cmb[i - 1][j - 1] + cmb[i - 1][j];
		}
	}

	int n;
	cin >> n;
	int a[n + 1];
	for (int i = n; i >= 0; i--)
		cin >> a[i];

	int64 p[n + 1];
	for (int i = 0; i < n + 1; i++) {
		p[i] = a[0];
		for (int j = 1; j < n + 1; j++)
			p[i] += a[j] * pow(i, j);
	}

	int64 c[n + 1];
	c[0] = p[0];
	for (int i = 1; i < n + 1; i++) {
		c[i] = p[i] - p[i - 1];
		for (int j = 0; j < i - 1; j++)
			c[i] -= cmb[i - 1][j] * c[j + 1];
	}

	for (int i = 0; i < n + 1; i++)
		cout << c[i] << " ";
	cout << endl;
}