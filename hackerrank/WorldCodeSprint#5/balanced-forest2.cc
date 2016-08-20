#include <iostream>
#include <vector>
#include <unordered_map>
#include <cstdint>
#include <climits>

using namespace std;
using Cache<T> = unordered_map<T, vector<int>>;

const int MAX = 5e4;
int n, c[MAX + 1], parent[MAX + 1];
uint64_t w[MAX + 1];

void Explore(int node, Cache<int>& graph, Cache<uint64_t>& cache) {
  w[node] = c[node];
  for (int child : graph[node]) {
    if (parent[child] == -1) {
      parent[child] = node;
      Explore(child, graph, cache);
      w[node] += w[child];
    }
  }
  cache[w[node]].push_back(node);
}

void Solve() {
  cin >> n;
  for (int i = 1; i <= n; ++i)
    cin >> c[i];

  int x, y;
  Cache<int> graph;
  for (int i = 0; i < n - 1; ++i) {
    cin >> x >> y;
    graph[x].push_back(y);
    graph[y].push_back(x);
  }

  for (int i = 1; i <= n; ++i)
    parent[i] = -1;
  parent[1] = 0;
  Cache<uint64_t> cache;
  Explore(1, graph, cache);

  uint64_t ans = UINT64_MAX;
  uint64_t total = w[1];
  for (int i = 1; i <= n; ++i) {
    if (total - w[i] < w[i])
      continue;
    // Connect i to new node.
    if ((w[i] < total / 3) || (w[i] == total / 3 && total % 3 == 0)) {
      if ((total - w[i]) % 2 != 0)
	continue;
      uint64_t weight = (total - w[i]) / 2;
      if (memo.find(weight) != memo.end()) {
	ans = min(ans, weight - w[i]);
	continue;
      }

      bool ok = false;
      int cur = parent[i];
      while (cur != 1) {
	if (w[cur] - w[i] == weight) {
	  ok = true;
	  break;
	}
	cur = parent[cur];
      }
      if (ok)
	ans = min(ans, weight - w[i]);
    } else {  // No new node connects to i.
      uint64_t weight = w[i];
      if (memo[weight].size() > 1) {
	ans = min(ans, 3 * weight - total);
	continue;
      }
      
      bool ok = false;
      int cur = parent[i];
      while (cur != 0) {
	if (w[cur] == 2 * weight) {
	  ok = true;
	  break;
	}
	if (parent[cur] == 1) {
	  if (w[cur] + c[1] == 2 * weight) {
	    ok = true;
	    break;
	  }
	}
	cur = parent[cur];
      }
    }
  }
}
	

  
int main() {
  int q;
  cin >> q;
  for (int i = 0; i < q; ++i)
    Solve();
}
