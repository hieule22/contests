#include <iostream>

using namespace std;
typedef long long int64;
const int MAX_N = 1e3;
const int64 MOD = 1e9 + 7;
int m, n, s, a[MAX_N];
int64 ways[MAX_N][2]; 

int main()
{
	ios_base::sync_with_stdio(0);

	int t; cin >> t;
	for (int tt = 0; tt < t; tt++) {
		cin >> n >> m >> s;
		for (int i = 0; i < m; i++)
			cin >> a[i];
		for (int i = 0; i < n; i++)
			ways[i][0] = ways[i][1] = 0;
		ways[s - 1][0] = 1;

		int flag = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (j - a[i] >= 0)
					ways[j - a[i]][1 - flag] = (ways[j - a[i]][1 - flag] + ways[j][flag]) % MOD;
				if (j + a[i] < n)
					ways[j + a[i]][1 - flag] = (ways[j + a[i]][1 - flag] + ways[j][flag]) % MOD;
				ways[j][flag] = 0;
			}
			flag = 1 - flag;
		}

		for (int i = 0; i < n; i++)
			cout << ways[i][flag] << " ";
		cout << endl;
	}
}