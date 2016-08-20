#include <iostream>
#include <climits>
#include <unordered_set>
#include <unordered_map>
#include <vector>

using namespace std;

int n, m, k;
unordered_map<int, vector<pair<int, int> > > graph;
unordered_set<int> storage;

int main() {
  ios_base::sync_with_stdio(0);
  cin >> n >> m >> k;
  int u, v, l, a;
  for (int i = 0; i < m; ++i) {
    cin >> u >> v >> l;
    graph[u].push_back(make_pair(v, l));
    graph[v].push_back(make_pair(u, l));
  }
  for (int i = 0; i < k; ++i) {
    cin >> a;
    storage.insert(a);
  }

  int pay = INT_MAX;
  for (int store : storage) {
    for (auto& neighbor : graph[store]) {
      if (storage.find(neighbor.first) == storage.end())
        pay = min(pay, neighbor.second);
    }
  }
  
  if (pay == INT_MAX)
    cout << -1 << endl;
  else
    cout << pay << endl;
  
  return 0;
}
