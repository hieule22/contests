#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include <unordered_map>

using namespace std;

const int MAX = 2048;

int n, m;
unordered_map<int, vector<pair<int, int> > > adj;
int a, b;

bool check(int weight)
{
	bool visited[n + 1];
	for (int i = 1; i <= n; i++)
		visited[i] = false;

	queue<int> frontier;
	frontier.push(a);

	while (!frontier.empty()) {
		int cur = frontier.front();
		frontier.pop();
		if (cur == b)
			return true;

		visited[cur] = true;

		for (auto & edge : adj[cur]) {
			if (!visited[edge.first] && edge.second <= weight) {
				int value = edge.second;
				bool ok = true;
				for (int i = 0; i <= 10; i++) {
					int mask = (1 << i);
					if ((value & mask) && !(weight & mask)) {
						ok = false;
						break;
					}
				}

				if (ok)
					frontier.push(edge.first);
			}
		}
	}

	return false;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin >> n >> m;

	int u, v, c;
	for (int i = 0; i < m; i++) {
		cin >> u >> v >> c;
		adj[u].push_back(make_pair(v, c));
		adj[v].push_back(make_pair(u, c));
	}

	cin >> a >> b;

	int res;
	for (res = 0; res < MAX; res++) {
		if (check(res))
			break;
	}

	if (res == MAX)
		cout << -1 << endl;
	else
		cout << res << endl;
}

