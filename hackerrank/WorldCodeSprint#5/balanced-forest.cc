#include <iostream>
#include <vector>
#include <unordered_map>
#include <cstdint>
#include <climits>
#include <unordered_set>
using namespace std;

using AdjList = unordered_map<int, vector<int> >;

int n;
int c[50001], parent[50001];
uint64_t w[50001];

bool IsAncestor(int x, int y) {
	while (y != 0) {
		if (y == x)
			return true;
		y = parent[y];
	}
	return false;
}

void Explore(int node, AdjList& graph, 
			unordered_map<uint64_t, vector<int> >& memo) {
	w[node] = c[node];
	for (int child : graph[node]) {
		if (parent[child] == -1) {
			parent[child] = node;
			Explore(child, graph, memo);
			w[node] += w[child];
		}
	}
	memo[w[node]].push_back(node);
}

void Solve() {
	cin >> n;
	for (int i = 1; i <= n; ++i)
		cin >> c[i];

	AdjList graph;
	int x, y;
	for (int i = 0; i < n - 1; ++i) {
		cin >> x >> y;
		graph[x].push_back(y);
		graph[y].push_back(x);
	}

	for (int i = 1; i <= n; ++i)
		parent[i] = -1;
	parent[1] = 0;
	unordered_map<uint64_t, vector<int> > memo;
	Explore(1, graph, memo);

	uint64_t ans = UINT64_MAX;
	uint64_t total = w[1];
	for (int i = 1; i <= n; ++i) {
		if (total - w[i] < w[i])
			continue;

		if ((w[i] < total / 3) || (w[i] == total / 3 && total % 3 != 0)) {
			uint64_t remain = total - w[i];
			if (remain % 2 == 1)
				continue;
			uint64_t weight = (remain / 2);
			bool found = false;

			unordered_set<int> parents;
			int cur = parent[i];
			while (cur != 1) {
				parents.insert(cur);
				if (w[cur] - w[i] == weight) {
					found = true;
					break;
				}
				cur = parent[cur];
			}
			if (found) {
				ans = min(ans, weight - w[i]);
				continue;
			}

			if (memo.find(weight) != memo.end()) {
				for (int node : memo[weight]) {
					if (parents.find(node) == parents.end()) {
						found = true;
						break;
					}
				}
			}
			if (found) 
				ans = min(ans, weight - w[i]);
		} else {
			uint64_t weight = w[i];
			bool found = false;
			if (memo[weight].size() > 1) {
				found = true;
			}
			if (found) {
				ans = min(ans, 3 * weight - total);
				continue;
			}
			int cur = parent[i];
			while (cur != 0) {
				if (w[cur] == 2 * weight) {
					found = true;
					break;
				}
				cur = parent[cur];	
			}
			if (found) {
				ans = min(ans, 3 * weight - total);
				continue;
			}
			auto iter = memo.find(total - 2 * weight);
			if (iter != memo.end()) {
				for (int node : iter->second) {
					if (!IsAncestor(i, node)) {
						found = true;
						break;
					}
				}
			}
			if (found)
				ans = min(ans, 3 * weight - total);
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