#include <iostream>
#include <cstdint>
#define min(a, b) ((a < b) ? a : b)

using namespace std;
int n;
int64_t m;
int p[100005];
int main()
{
	ios_base::sync_with_stdio(0);
	cin >> n >> m;
	for (int i = 1; i <= n; i++)
		cin >> p[i];

	int rank = 1, offset = 0;
	for (int i = 0; i < min(n, m); i++) {
		offset += (p[rank] - rank);
		rank = p[rank] + 1;
		if (rank > n)
			break;
	}

	cout << (m + offset) << endl;
}