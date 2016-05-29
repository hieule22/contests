#include <iostream>
#include <cstring>
#define min(a, b) ((a < b) ? a : b)

using namespace std;

int n;
char line[2000005];

void solve()
{
	cin >> n >> line;

	int bFirst = 0, gFirst = 0;
	for (int i = 0; i < 2 * n; i += 2) {
		if (line[i] == 'B')
			gFirst++;
		else
			bFirst++;
	}

	cout << min(bFirst, gFirst) << endl;
}

int main()
{
	ios_base::sync_with_stdio(0);
	int t;
	cin >> t;
	for (int i = 0; i < t; i++)
		solve();
	return 0;
}