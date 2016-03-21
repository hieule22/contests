#include <iostream>
#include <string.h>

using namespace std;
typedef unsigned long long uint64;

const uint64 MOD = 1e9 + 7;
const int MAX_LEN = 1e5 + 5;
char s[MAX_LEN];
int length;

uint64 fact(int n)
{
	uint64 res = 1;
	for (int i = 1; i <= n; i++)
		res = res * i % MOD;
	return res;
}

uint64 choose(int p, int r)
{
	if (r > p) return 0;
	uint64 res = 1;
	for (int i = 1; i <= r; i++) 
		res = res * (p - i + 1) / i % MOD;
	return res;
}

int main()
{
	int t;
	cin >> t;

	for (int tt = 0; tt < t; tt++)
	{
		cin >> s;
		length = strlen(s);
		uint64 cnt = fact(length);
		uint64 total = cnt * cnt % MOD;
		uint64 complement = cnt * choose(length, 2) % MOD;
		total = (total + MOD - complement * 3) % MOD;
		cout << total << endl;
	}
}