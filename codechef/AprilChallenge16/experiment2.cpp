#include <stdio.h>
#include <vector>
#include <deque>
#include <stdlib.h>
#include <time.h>
#include <iostream>

using namespace std;
const int MAX_N = 1e5 + 5;
const int DUMMY = -1;
const int ROOT = 1;
int n;
int parent[MAX_N], depth[MAX_N];
int nodeCost[MAX_N], treeCost[MAX_N];

void findParent(vector<vector<pair<int, int> > > & graph,
				vector<int> & levels)
{
	for (int i = 1; i < n + 1; i++)
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
	// scanf("%d", &n);
	n = 1e5;
	vector<vector<pair<int, int> > > graph;
	graph.resize(n + 1);

	srand(time(NULL));
	int u, v, w;
	for (int i = 0; i < n - 1; i++) {
		// scanf("%d %d %d", &u, &v, &w);
		u = i + 1;
		v = i + 2;
		w = rand() % 10000 + 1;
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
			if (parent[node] == child.first) continue;
			int childDepth = child.second + depth[child.first];
			if (childDepth > firstDepth) {
				secondDepth = firstDepth;
				firstDepth = childDepth;
			} else if (childDepth > secondDepth) {
				secondDepth = childDepth;
			}
		}
		// Update strategic cost and depth of this node
		treeCost[node] = nodeCost[node] = depth[node] = firstDepth;
		for (auto & child : graph[node]) {
			if (parent[node] == child.first) continue;
			if (depth[child.first] + child.second == firstDepth)
				nodeCost[child.first] = max(nodeCost[child.first], child.second + secondDepth);
			else
				nodeCost[child.first] = max(nodeCost[child.first], child.second + firstDepth);

			treeCost[node] = min(treeCost[node], nodeCost[child.first]);
		}

		for (auto & child : graph[node]) {
			if (parent[node] == child.first) continue;
			if (treeCost[node] < treeCost[child.first]) continue;
			if (depth[child.first] + child.second != firstDepth) continue;

			descendant[node].push_back(make_pair(child.first, child.second));

			while (!descendant[child.first].empty()) {
				auto gc = descendant[child.first].front();
				descendant[child.first].pop_front();
				int depthFromParent = gc.second + child.second;
				if (depth[gc.first] + depthFromParent == firstDepth)
					nodeCost[gc.first] = max(nodeCost[gc.first], depthFromParent + secondDepth);
				else
					nodeCost[gc.first] = max(nodeCost[gc.first], depthFromParent + firstDepth);

				if (treeCost[node] > nodeCost[gc.first]) {
					treeCost[node] = nodeCost[gc.first];
					descendant[node].push_back(make_pair(gc.first, depthFromParent));
				}
			}
		}
	}

	// for (int i = 1; i < n + 1; i++)
	// 	printf("%d\n", treeCost[i]);
}

int main()
{
	int tests;
	// scanf("%d", &tests);
	tests = 1;
	for (int t = 1; t <= tests; t++)
		solve();
	cerr << "Time elapsed: " << 1.0 * clock() / CLOCKS_PER_SEC << " s.\n";
}