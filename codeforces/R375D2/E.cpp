#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;
const int MAX_N = 200;
using AdjList = unordered_map<int, vector<int>>;

int n, m, result;
int degree[MAX_N + 1];
int visited[MAX_N + 1];
int balance[MAX_N + 1];
int matrix[MAX_N + 1][MAX_N + 1];

void dfs(AdjList& graph, int node) {
  if (visited[node] || degree[node] % 2 == 1)
    return;
  
  int balance = 0;
  for (int neighbor : graph[node]) {
    if (matrix[node][neighbor] == 1)
      balance++;
    else if (matrix[neighbor][node] == 1)
      balance--;
  }

  for (int neighbor : graph[node]) {
    if (matrix[node][neighbor] == 0 && matrix[neighbor][node] == 0) {
      if (balance < 0) {
        matrix[node][neighbor] = 1;
        balance++;
      } else {
        matrix[neighbor][node] = 1;
        balance--;
      }
    }
  }

  if (balance == 0)
    result++;
  
  visited[node] = true;
  for (int neighbor : graph[node])
    dfs(graph, neighbor);
}

void solve() {
  cin >> n >> m;
  AdjList graph;
  for (int i = 1; i <= n; ++i) {
    degree[i] = visited[i] = balance[i] = 0;
    for (int j = 1; j <= n; ++j)
      matrix[i][j] = 0;
  }

  int u, v;
  for (int i = 0; i < m; ++i) {
    cin >> u >> v;
    graph[u].push_back(v);
    graph[v].push_back(u);
    degree[u]++;
    degree[v]++;
  }

  result = 0;
  for (int i = 1; i <= n; ++i) {
    dfs(graph, i);
  }

  cout << result << endl;

  for (int i = 1; i <= n; ++i) {
    if (graph.find(i) == graph.end()) {
      result++;
      continue;
    }
    for (int j : graph[i]) {
      if (j < i)
        continue;
      if (matrix[i][j] == 1)
        cout << i << " " << j << endl;
      else
        cout << j << " " << i << endl;
    }
  }
}

int main() {
  int t;
  cin >> t;
  for (int i = 0; i < t; ++i)
    solve();
}
