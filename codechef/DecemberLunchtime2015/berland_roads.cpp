#include <iostream>
#include <vector>
#include <unordered_set>
#include <unordered_map>
#include <map>
#include <climits>
using namespace std;
const int MAX = 5 * 100000 + 5;
const int flag = INT_MAX;

typedef unsigned long long uint64;

int city[MAX];
int visited[MAX];
int n, m, q;

vector<pair<int, int> > roads(MAX);

int bfs(int cur, unordered_map<int, unordered_set<int> > &adj, uint64 &total, int region) {
	total += city[cur];
	visited[cur] = region;
	for (int neighbor : adj[cur]) {
		if (visited[neighbor] != region) 
			bfs(neighbor, adj, total, region);
	}
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin >> n >> m >> q;
	for (int i = 1; i <= n; i++)
		cin >> city[i];

	unordered_map<int, unordered_set<int> > adj;
	int x, y;
	for (int i = 1; i <= m; i++) {
		cin >> x >> y;
		roads[i] = make_pair(x, y);
		adj[x].insert(y);
		adj[y].insert(x);
	}

	for (int i = 1; i <= n; i++)
		visited[i] = 0;


	vector<uint64> populace;
	map<uint64, int> res;
	for (int i = 1; i <= n; i++) {
		if (!visited[i]) {
			uint64 total = 0;
			int region = populace.size();
			bfs(i, adj, total, region);
			populace.push_back(total);
			res[total]++;
		}
	}

	char query;
	int k, a;
	for (int i = 0; i < q; i++) {
		cin >> query;
		if (query == 'P') {
			cin >> a >> x;
			res[populace[visited[a]]]--;
			if (res[populace[visited[a]]] == 0)
				res.erase(populace[visited[a]]);

			populace[visited[a]] += (x - city[a]);
			res[populace[visited[a]]]++;
			city[a] = x;
			cout << res.rbegin()->first << endl;
		} else {
			cin >> k;
			x = roads[k].first;
			y = roads[k].second;
			adj[x].erase(y);
			adj[y].erase(x);
			uint64 total = 0;
			int orig = visited[x];

			res[populace[orig]]--;
			if (res[populace[orig]] == 0)
				res.erase(populace[orig]);

			int region = populace.size();
			bfs(x, adj, total, region);
			populace.push_back(total);
			populace[orig] -= total;

			res[populace[orig]]++;
			res[total]++;
			cout << res.rbegin()->first << endl;
		}
	}
}