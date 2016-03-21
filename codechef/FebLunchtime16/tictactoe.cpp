#include <iostream>

using namespace std;

char grid[25][25];
int n, k;

const char X = 'X';
const char EMPTY = '.';
const char O = 'O';

void solve();

bool check(int x, int y, int dx, int dy)
{	
	bool taken;
	if (grid[x][y] == X)
		taken = false;
	else if (grid[x][y] == EMPTY)
		taken = true;
	else
		return false;

	for (int i = 1; i < k; i++) {
		x += dx;
		y += dy;

		if (x < 0 || x >= n || y < 0 || y >= n)
			return false;
		if (grid[x][y] == O)
			return false;
		if (grid[x][y] == EMPTY && taken)
			return false;
		if (grid[x][y] == EMPTY)
			taken = true;
	}

	return true;
}

int main()
{
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;

	for (int tt = 0; tt < t; tt++)
	{
		solve();	
	}
}

void solve()
{
	cin >> n >> k;
		for (int i = 0; i < n; i++)
			cin >> grid[i];

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (check(i, j, 0, 1) ||
				check(i, j, 0, -1) ||
				check(i, j, 1, 0) ||
				check(i, j, -1, 0) ||
				check(i, j, 1, 1) ||
				check(i, j, 1, -1) ||
				check(i, j, -1, 1) ||
				check(i, j, -1, -1)) {
				cout << "YES" << endl;
				return;
			}
		}
	}

	cout << "NO" << endl;
}