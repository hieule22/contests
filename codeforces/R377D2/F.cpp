#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

const int MAX = 400000 + 1;

vector<vector<pair<int, int>>> graph;
int visited[MAX], dfsNum[MAX], dfsLow[MAX];
int maxSize, maxRoot, dfsNumCounter;
vector<pair<int, int>> edges;
vector<int> stack;

void dfs(int parent, int u) {
  dfsLow[u] = dfsNum[u] = ++dfsNumCounter;
  stack.push_back(u);
  visited[u] = 1;
  for (const auto& edge : graph[u]) {
    int v = edge.first, index = edge.second;
    if (v == parent)
      continue;
    if (dfsNum[v] == 0) {
      dfs(u, v);
      edges[index] = {v, u};
    } else {
      edges[index] = {u, v};
    }

    if (visited[v])
      dfsLow[u] = min(dfsLow[u], dfsLow[v]);
  }

  if (dfsLow[u] == dfsNum[u]) {
    int cur = 0;
    int v;
    do {
      v = *stack.rbegin();
      stack.pop_back();
      visited[v] = 0;
      cur++;
    } while (u != v);
      if (maxSize < cur) {
      maxSize = cur;
      maxRoot = u;
    }
  }
}

int main() {
  int n, m;
  cin >> n >> m;
  graph.resize(n + 1);
  edges.resize(m);

  int u, v;
  for (int i = 0; i < m; ++i) {
    cin >> u >> v;
    graph[u].push_back({v, i});
    graph[v].push_back({u, i});
  }

  memset(dfsNum, 0, sizeof dfsNum);
  memset(dfsLow, 0, sizeof dfsLow);
  memset(visited, 0, sizeof visited);
  dfsNumCounter = maxSize = maxRoot = 0;
  dfs(0, 1);

  memset(dfsNum, 0, sizeof dfsNum);
  memset(dfsLow, 0, sizeof dfsLow);
  memset(visited, 0, sizeof visited);
  dfsNumCounter = 0;
  dfs(0, maxRoot);

  cout << maxSize << endl;
  for (const auto& edge : edges)
    cout << edge.first << " " << edge.second << endl;
}
