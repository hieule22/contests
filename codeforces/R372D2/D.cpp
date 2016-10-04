#include <iostream>
#include <cstdint>
#include <vector>
#include <cstring>
#include <queue>

using namespace std;

const int MAX_N = 1000;
int n, m, L, s, t;
int weight[MAX_N][MAX_N];
vector<int> neighbors[MAX_N];
int dist[MAX_N], visited[MAX_N];

struct Comparator {
  bool operator()(const int a, const int b) const {
    return dist[b] < dist[a];
  }
};

int dijkstra(bool flag) {
  for (int i = 0; i < n; ++i) {
    visited[i] = 0;
    dist[i] = INT_MAX;
  }

  priority_queue<int, vector<int>, Comparator> frontier;
  dist[s] = 0;
  frontier.push(s);

  while (!frontier.empty()) {
    int top = frontier.top();
    frontier.pop();
    if (visited[top]) {
      continue;
    }
    visited[top] = true;
    
    if (top == t) {
      return dist[top];
    }

    for (int node : neighbors[top]) {
      if (!flag && weight[top][node] == 0) {
        continue;
      }
      int temp = dist[top] + weight[top][node];
      if (temp < dist[node]) {
        dist[node] = temp;
        frontier.push(node);
      }
    }
  }

  return INT_MAX;
}

int main() {
  cin >> n >> m >> L >> s >> t;
  int u, v, w;
  for (int i = 0; i < m; ++i) {
    cin >> u >> v >> w;
    neighbors[u].push_back(v);
    neighbors[v].push_back(u);
    w = (w == 0) ? -1 : w;
  }

  int length = dijkstra(false);
  if (length < L) {
    cout << "NO" << endl;
  }

  if (length == L) {
    cout << "YES" << endl;
  }

  int length 
}


