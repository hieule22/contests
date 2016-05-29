#include <iostream>
#include <climits>
#include <queue>
#include <vector>
#define min(a, b) ((a < b) ? a : b)
#define max(a, b) ((a > b) ? a : b)

using namespace std;
typedef unsigned int uint;
uint m, n, q, cell[7][5000], temp[7][5000];
uint dist[7][5000][7][500];
bool visited[7][5000];
const uint sep = 700;

void dijkstra(uint, uint);

int main()
{
	ios_base::sync_with_stdio(0);
	cin >> n >> m;
	for (uint i = 0; i < n; i++)
		for (uint j = 0; j < m; j++)
			cin >> cell[i][j];

	uint cols = m / sep;
	for (uint i = 0; i < n; i++) for (uint j = 0; j < m; j++)
			for (uint r = 0; r < n; r++) for (uint c = 0; c < cols; c++)
				dist[i][j][r][c] = UINT_MAX;

	for (uint r = 0; r < n; r++)
		for (uint c = 0; c < cols; c++)
			dijkstra(r, c);

	cin >> q;
	uint r1, c1, r2, c2, res;
	for (uint qq = 0; qq < q; qq++) {
		cin >> r1 >> c1 >> r2 >> c2;
		if (c1 / sep != c2 / sep) {
			uint maxCol = max(c1 / sep, c2 / sep);
			res = UINT_MAX;
			for (uint r = 0; r < n; r++) 
				res = min(res, dist[r1][c1][r][maxCol] + dist[r2][c2][r][maxCol] - cell[r][maxCol * sep]);
		} else {
			res = UINT_MAX;
			uint minCol = c1 / sep;
			uint maxCol = min(m - 1, minCol * sep + sep);

			for (uint i = 0; i < n; i++)
				for (uint j = minCol; j <= maxCol; j++) {
					visited[i][j] = false;
					temp[i][j] = UINT_MAX;
				}

			auto comparator = [] (const pair<uint, uint> & lhs, const pair<uint, uint> & rhs)
								-> bool {
									return temp[lhs.first][lhs.second] > temp[rhs.first][rhs.second];
								};
			priority_queue<pair<uint, uint>, vector<pair<uint, uint> >,
								decltype(comparator)> frontier(comparator);
			temp[r1][c1] = cell[r1][c1];
			frontier.push(make_pair(r1, c1));
			while (!frontier.empty()) {
				auto top = frontier.top(); frontier.pop();
				uint r = top.first, c = top.second;
				// cout << r << " " << c << " " << temp[r][c] << endl;
				if (visited[r][c]) continue;
				if (r == r2 && c == c2) break;
				visited[r][c] = true;
				if (r > 0 && !visited[r - 1][c]) {
					temp[r - 1][c] = min(temp[r - 1][c], temp[r][c] + cell[r - 1][c]);
					frontier.push(make_pair(r - 1, c));
				}
				if (r < n - 1 && !visited[r + 1][c]) {
					temp[r + 1][c] = min(temp[r + 1][c], temp[r][c] + cell[r + 1][c]);
					frontier.push(make_pair(r + 1, c));
				}
				if (c > minCol && !visited[r][c - 1]) {
					temp[r][c - 1] = min(temp[r][c - 1], temp[r][c] + cell[r][c - 1]);
					frontier.push(make_pair(r, c - 1));
				}
				if (c < maxCol && !visited[r][c + 1]) {
					temp[r][c + 1] = min(temp[r][c + 1], temp[r][c] + cell[r][c + 1]);
					frontier.push(make_pair(r, c + 1));
				}
			}
			res = min(res, temp[r2][c2]);
		}
		cout << res << endl;
	}
}

void dijkstra(uint row, uint col)
{
	
	for (uint i = 0; i < n; i++)
		for (uint j = 0; j < m; j++)
			visited[i][j] = false;

	auto comparator	= [row, col] (const pair<uint, uint> & lhs, const pair<uint, uint> & rhs)
								-> bool {
									return dist[lhs.first][lhs.second][row][col] > 
										dist[rhs.first][rhs.second][row][col];
								};

	priority_queue<pair<uint, uint>, vector<pair<uint, uint> >, 
						decltype(comparator)> frontier(comparator);
	dist[row][col * sep][row][col] = cell[row][col * sep];
	frontier.push(make_pair(row, col * sep));


	while (!frontier.empty()) {
		auto top = frontier.top(); frontier.pop();
		uint r = top.first, c = top.second;
		if (visited[r][c]) continue;
		visited[r][c] = true;
		uint distance = dist[r][c][row][col];
		if (r > 0 && !visited[r - 1][c]) {
			dist[r - 1][c][row][col] = min(dist[r - 1][c][row][col], distance + cell[r - 1][c]);
			frontier.push(make_pair(r - 1, c));
		}
		if (r < n - 1 && !visited[r + 1][c]) {
			dist[r + 1][c][row][col] = min(dist[r + 1][c][row][col], distance + cell[r + 1][c]);
			frontier.push(make_pair(r + 1, c));
		}
		if (c > 0 && !visited[r][c - 1]) {
			dist[r][c - 1][row][col] = min(dist[r][c - 1][row][col], distance + cell[r][c - 1]);
			frontier.push(make_pair(r, c - 1));
		}
		if (c < m - 1 && !visited[r][c + 1]) {
			dist[r][c + 1][row][col] = min(dist[r][c + 1][row][col], distance + cell[r][c + 1]);
			frontier.push(make_pair(r, c + 1));
		}
	}
}