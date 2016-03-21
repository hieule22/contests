#include <iostream>

using namespace std;
const int MAX = (int)1e5;
const int MOD = (int)1e9 + 7;

int main()
{
	int alpha[MAX + 1];
	int beta[MAX + 1];

	alpha[0] = beta[1] = 1;
	alpha[1] = beta[0] = 0;

	for (int i = 2; i <= MAX; i++)
	{
		alpha[i] = (alpha[i - 1] + alpha[i - 2]) % MOD;
		beta[i] = (beta[i - 1] + beta[i - 2]) % MOD;
		if (i - 3 < 0) {
			alpha[i] = (alpha[i] + 1) % MOD;
		} else {
			alpha[i] = (alpha[i] + alpha[i - 3]) % MOD;
			beta[i] = (beta[i] + beta[i - 3]) % MOD;
		}
	}

	int t;
	cin >> t;
	for (int i = 0; i < t; i++)
	{
		int ti;
		cin >> ti;
		cout << alpha[ti] << " " << beta[ti] << endl;
	}
}