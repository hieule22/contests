#include <iostream>
#include <vector>
#include <climits>
#include <queue>
#include <algorithm>

using namespace std;
int n, m, q;
int cells[7][5000];
bool visited[7][5000];

int getCol(int, int);

int main()
{
	cin >> n >> m;
	for (int i = 0; i < n; i++)
		for (int j = 0; j < m; j++)
			cin >> cells[i][j];

	bool chosen[m];
	for (int i = 0; i < m; i++) chosen[i] = false;
	vector<vector<int> > queries;
	int r1, c1, r2, c2;
	cin >> q;
	for (int i = 0; i < q; i++) {
		cin >> r1 >> c1 >> r2 >> c2;
		chosen[getCol(c1, c2)] = true;
		queries.push_back(vector<int>{r1, c1, r2, c2});
	}

	vector<int> mid;
	for (int i = 0; i < m; i++)
		if (chosen[i])
			mid.push_back(i);

	int dist[7][mid.size()][7][5000];
	for (int i = 0; i < n; i++)
		for (int j = 0; j < mid.size(); j++)
			for (int k = 0; k < n; k++)
				for (int h = 0; h < m; h++)
					dist[i][j][k][h] = INT_MAX;

	for (int r = 0; r < n; r++) {
		for (int cc = 0; cc < mid.size(); cc++) {
			int c = mid[cc];

			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					visited[i][j] = false;

			auto comp = [&, r, cc] (const pair<uint, uint> & lhs, const pair<uint, uint> & rhs)
				-> bool {
					return dist[r][cc][lhs.first][lhs.second] > 
						dist[r][cc][rhs.first][rhs.second];
				};
			priority_queue<pair<uint, uint>, vector<pair<int, int> >, decltype(comp)> frontier(comp);
			dist[r][cc][r][c] = cells[r][c];
			frontier.push(make_pair(r, c));
			while (!frontier.empty()) {
				auto top = frontier.top(); frontier.pop();
				uint row = top.first, col = top.second;
				if (visited[row][col]) continue;
				visited[row][col] = true;
				int distance = dist[r][cc][row][col];
				if (row > 0 && !visited[row - 1][col]) {
					dist[r][cc][row - 1][col] = min(dist[r][cc][row - 1][col], distance + cells[row - 1][col]);
					frontier.push(make_pair(row - 1, col));
				}
				if (row < n - 1 && !visited[row + 1][col]) {
					dist[r][cc][row + 1][col] = min(dist[r][cc][row + 1][col], distance + cells[row + 1][col]);
					frontier.push(make_pair(row + 1, col));
				}
				if (col > 0 && !visited[row][col - 1]) {
					dist[r][cc][row][col - 1] = min(dist[r][cc][row][col - 1], distance + cells[row][col - 1]);
					frontier.push(make_pair(row, col - 1));
				}
				if (col < m - 1 && !visited[row][col + 1]) {
					dist[r][cc][row][col + 1] = min(dist[r][cc][row][col + 1], distance + cells[row][col + 1]);
					frontier.push(make_pair(row, col + 1));
				}
			}
		}
	}

	for (auto & q : queries) {
		r1 = q[0]; c1 = q[1]; r2 = q[2]; c2 = q[3];
		int split = getCol(c1, c2);
		int cc = lower_bound(mid.begin(), mid.end(), split) - mid.begin();
		int res = INT_MAX;
		for (int r = 0; r < n; r++)
			res = min(res, dist[r][cc][r1][c1] + dist[r][cc][r2][c2] - cells[r][split]);
		cout << res << endl;
	}
}

int getCol(int c1, int c2)
{
	int low = 0, high = m - 1, mid;
	while (low <= high) {
		mid = (low + high) / 2;
		if (mid >= c1 && mid <= c2)
			return mid;
		else if (mid < c1)
			low = mid + 1;
		else 
			high = mid - 1;
	}
	return -1;
}