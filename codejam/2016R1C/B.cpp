#include <iostream>

using namespace std;
typedef unsigned long long uint64;
int b;
uint64 m;

void solve(int test)
{
	cin >> b >> m;
	uint64 limit = (1ULL << (b - 2));

	cout << "Case " << "#" << test << ": ";
	if (limit < m) {
		cout << "IMPOSSIBLE" << endl;
		return;
	}

	cout << "POSSIBLE" << endl;
	m--;
	for (int i = 1; i < b; i++) {
		for (int j = 1; j <= i; j++)
			cout << "0";
		for (int j = i + 1; j < b; j++)
			cout << "1";
		if (i == 1 || (m & (1ULL << (i - 2))))
			cout << "1" << endl;
		else 
			cout << "0" << endl;
	}

	for (int i = 1; i <= b; i++)
		cout << "0";
	cout << endl;
}

int main()
{
	ios_base::sync_with_stdio(0);
	int test; cin >> test;
	for (int tt = 1; tt <= test; tt++)
		solve(tt);
	return 0;
}