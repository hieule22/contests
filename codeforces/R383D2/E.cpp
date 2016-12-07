#include <cstring>
#include <cstdio>
#include <vector>

using namespace std;

bool dfs(int node, int colors[], const vector<vector<int>>& adj, int type) {
  if (colors[node] == 3 - type)
    return false;
  if (colors[node] == type)
    return true;
  colors[node] = type;
  for (int neighbor : adj[node]) {
    if (!dfs(neighbor, colors, adj, 3 - type))
      return false;
  }
  return true;
}

int main() {
  int n;
  scanf("%d", &n);
  vector<vector<int>> adj(2 * n + 1);
  vector<pair<int, int>> couples;
  int a, b;
  for (int i = 0; i < n; ++i) {
    scanf("%d %d", &a, &b);
    couples.push_back({a, b});
    adj[a].push_back(b);
    adj[b].push_back(a);
  }

  for (int i = 1; i <= n; ++i) {
    int j = 2 * i - 1;
    adj[2 * i].push_back(j);
    adj[j].push_back(2 * i);
  }

  int colors[2 * n + 1];
  memset(colors, 0, sizeof colors);

  for (int i = 1; i <= 2 * n; ++i) {
    if (colors[i] == 0) {
      if (!dfs(i, colors, adj, 1)) {
        printf("-1\n");
        return 0;
      }
    }
  }

  for (const auto& couple : couples)
    printf("%d %d\n", colors[couple.first], colors[couple.second]);

  return 0;
}
