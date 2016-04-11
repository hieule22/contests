#include <stdio.h>
#include <vector>
#include <deque>
#include <queue>

using namespace std;
const int MAX_N = 1e5 + 5;
const int DUMMY = -1;
const int ROOT = 1;
int n, parent[MAX_N], depth[MAX_N];
int node_cost[MAX_N], tree_cost[MAX_N];

void findParent(vector<vector<pair<int, int> > > & graph, 
				vector<int> & levels)
{
	for (int i = 1; i <= n; i++)
		parent[i] = 0;
	parent[ROOT] = DUMMY;
	levels.push_back(ROOT);

	for (int i = 0; i < n; i++) {
		int node = levels[i];
		for (auto & child : graph[node]) {
			if (parent[child.first] == 0) {
				parent[child.first] = node;
				levels.push_back(child.first);
			}
		}
	}
}

void solve()
{
	scanf("%d", &n);
	vector<vector<pair<int, int> > > graph;
	graph.resize(n + 1);

	int u, v, w;
	for (int i = 0; i < n - 1; i++) {
		scanf("%d %d %d", &u, &v, &w);
		graph[u].push_back(make_pair(v, w));
		graph[v].push_back(make_pair(u, w));
	}

	vector<int> levels;
	findParent(graph, levels);

	vector<deque<pair<int, int> > > descendant;
	descendant.resize(n + 1);
	for (auto iter = levels.rbegin(); iter != levels.rend(); ++iter)
	{
		int node = *iter;
		// Find the two deepest subtrees rooted at node
		int firstDepth = 0, secondDepth = 0;
		for (auto & child : graph[node]) {
			if (parent[node] == child.first) 
				continue;
			int child_depth = child.second + depth[child.first];
			if (child_depth > firstDepth) {
				secondDepth = firstDepth;
				firstDepth = child_depth;
			} else if (child_depth > secondDepth) {
				secondDepth = child_depth;
			}
		}
		// Update strategic cost and depth of this node
		node_cost[node] = depth[node] = firstDepth;
		// Determine the strategic cost of subtree rooted at node
		tree_cost[node] = depth[node];
		for (auto & child : graph[node]) {
			if (parent[node] == child.first)
				continue;

			if (tree_cost[node] < tree_cost[child.first])
				continue;

			if (depth[child.first] + child.second == firstDepth) {
				node_cost[child.first] = max(node_cost[child.first], child.second + secondDepth);
			} else {
				node_cost[child.first] = max(node_cost[child.first], child.second + firstDepth);
				continue;
			}

			if (tree_cost[node] > node_cost[child.first]) {
				tree_cost[node] = node_cost[child.first];
				descendant[node].push_back(make_pair(child.first, child.second));
			} else {
				continue;
			}

			while (!descendant[child.first].empty()) {
				auto gc = descendant[child.first].front();
				descendant[child.first].pop_front();
				int depthFromParent = gc.second + child.second;
				if (depth[gc.first] + depthFromParent == firstDepth)
					node_cost[gc.first] = max(node_cost[gc.first], depthFromParent + secondDepth);
				else
					node_cost[gc.first] = max(node_cost[gc.first], depthFromParent + firstDepth);

				if (tree_cost[node] > node_cost[gc.first]) {
					tree_cost[node] = node_cost[gc.first];
					descendant[node].push_back(make_pair(gc.first, depthFromParent));
				}
			}
		}
	}

	for (int i = 1; i < n + 1; i++)
		printf("%d\n", tree_cost[i]);
}

int main()
{
	int tests;
	scanf("%d", &tests);
	for (int t = 1; t <= tests; t++)
		solve();
}