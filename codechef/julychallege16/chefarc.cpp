#include <iostream>
#include <climits>
#include <deque>

using namespace std;

int grid[100][100];
int a1[100][100], a2[100][100];
int n, m, k1, k2;

void Process(int a[100][100], int k, int x, int y) {
	deque<pair<int, int> > frontier;
	frontier.push_back(make_pair(x, y));
	int cnt = 0;
	a[x][y] = cnt;
	while (!frontier.empty()) {
		++cnt;
		int size = frontier.size();
		for (int i = 0; i < size; ++i) {
			auto front = frontier.front();
			frontier.pop_front();
			for (int d = 1; d <= k; ++d) {
				for (int dx = 0; dx <= d; ++dx) 
					for (int dy = 0; dy <= d - dx; ++dy) {
						x = front.first - dx;
						y = front.second - dy;
						if (x >= 0 && y >= 0 && a[x][y] == -1 && grid[x][y] == 0) {
							a[x][y] = cnt;
							frontier.push_back(make_pair(x, y));
						}

						x = front.first - dx;
						y = front.second + dy;
						if (x >= 0 && y < m && a[x][y] == -1 && grid[x][y] == 0) {
							a[x][y] = cnt;
							frontier.push_back(make_pair(x, y));
						}

						x = front.first + dx;
						y = front.second - dy;
						if (x < n && y >= 0 && a[x][y] == -1 && grid[x][y] == 0) {
							a[x][y] = cnt;
							frontier.push_back(make_pair(x, y));
						}

						x = front.first + dx;
						y = front.second + dy;
						if (x < n && y < m && a[x][y] == -1 && grid[x][y] == 0) {
							a[x][y] = cnt;
							frontier.push_back(make_pair(x, y));
						}
					}
			} 
		}
	}
}

void Solve() {
	cin >> n >> m >> k1 >> k2;
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j) {
			cin >> grid[i][j];
			a1[i][j] = a2[i][j] = -1; 
		}	
	Process(a1, k1, 0, 0);
	Process(a2, k2, 0, m - 1);

	int res = INT_MAX;
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < m; ++j) {
			if (a1[i][j] > -1 && a2[i][j] > -1) 
				res = min(res, max(a1[i][j], a2[i][j]));
		}
	}

	if (res == INT_MAX)
		res = -1;
	cout << res << endl;
}

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; ++i)
		Solve();
}