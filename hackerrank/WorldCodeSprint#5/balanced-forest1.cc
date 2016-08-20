#include <iostream>
#include <vector>
#include <unordered_map>
#include <cstdint>
#include <climits>
using namespace std;

using AdjList = unordered_map<int, vector<int> >;
using Edge = pair<int, int>;

int n;
int c[50001], parent[50001];
uint64_t w[500001];

void Explore(int node, AdjList& graph) {
	w[node] = c[node];
	for (int child : graph[node]) {
		if (parent[child] == -1) {
			parent[child] = node;
			Explore(child, graph);
			w[node] += w[child];
		}
	}
}

bool IsAncestor(int x, int y) {
	while (y != 0) {
		if (y == x)
			return true;
		y = parent[y];
	}
	return false;
}

void Validate(uint64_t a, uint64_t b, uint64_t c, uint64_t& res) {
	if (a == b && c <= a)
		res = min(res, a - c);
	else if (a == c && b <= a)
		res = min(res, a - b);
	else if (b == c && a <= b)
		res = min(res, b - a);
}

void Solve() {
	cin >> n;
	for (int i = 1; i <= n; ++i)
		cin >> c[i];
	int x, y;
	AdjList graph;
	vector<Edge> edges;
	for (int i = 0; i < n - 1; ++i) {
		cin >> x >> y;
		graph[x].push_back(y);
		graph[y].push_back(x);
		edges.push_back(make_pair(x, y));
	}
	for (int i = 1; i <= n; ++i)
		parent[i] = -1;
	parent[1] = 0;
	Explore(1, graph);

	uint64_t ans = UINT64_MAX;
	for (int i = 0; i < n - 1; ++i) {
		x = edges[i].first;
		y = edges[i].second;
		if (parent[x] == y) swap(x, y);
		uint64_t a = w[y], b = w[1] - w[y];
		if (a == b)
			ans = min(ans, a);
		for (int j = i + 1; j < n - 1; ++j) {
			int m = edges[j].first, n = edges[j].second;
			if (parent[m] == n) swap(m, n);
			if (IsAncestor(y, m)) {
				uint64_t c = w[n];
				uint64_t d = a - c;
				Validate(b, c, d, ans);
			} else if (IsAncestor(n, x)) {
				uint64_t c = w[n] - w[y];
				uint64_t d = w[1] - w[n];
				Validate(w[y], c, d, ans);
			} else {
				uint64_t c = w[n];
				uint64_t d = b - c;
				Validate(a, c, d, ans);
			}
		}
	}
	if (ans == UINT64_MAX)
		cout << -1 << endl;
	else
		cout << ans << endl;
}

int main() {
	int q;
	cin >> q;
	for (int i = 0; i < q; ++i)
		Solve();
}