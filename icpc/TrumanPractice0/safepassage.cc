#include <iostream>
#include <climits>
#include <queue>
#include <algorithm>
#include <vector>

#define is_bit_set(n, a) ((n & (1 << a)) != 0)

using namespace std;

const int MAX_N = 15;
int n, time_[MAX_N];
vector<vector<pair<int, int>>> adj_list;

struct Comparator {
  Comparator(int *dist) : dist_(dist) {}

  bool operator() (const int a, const int b) const {
    return dist_[a] > dist_[b];
  }
  
  int *dist_;
};

int main() {
  cin >> n;
  for (int i = 0; i < n; ++i)
    cin >> time_[i];
  adj_list.resize(1 << (n + 1));

  const int MAX_MASK = (1 << n);
  for (int i = 0; i < (1 << (n + 1)); ++i) {
    int cloak = (i & 1);
    int left_mask = (i >> 1);
    int mask = (cloak == 0) ? left_mask : (MAX_MASK - 1 - left_mask);

    for (int j = 0; j < n; ++j) {
      if (!is_bit_set(mask, j))
        continue;
      for (int k = 0; k < n; ++k) {
        if (!is_bit_set(mask, k))
          continue;
        int new_mask = (mask & ~(1 << j) & ~(1 << k));
        new_mask = (cloak == 0) ? new_mask : (MAX_MASK - 1 - new_mask);
        new_mask = (new_mask << 1) + (1 - cloak);
        
        int dist = max(time_[j], time_[k]);
        adj_list[i].push_back({new_mask, dist});
      }
    }
  }

  int src = ((MAX_MASK - 1) << 1) + 0;
  int target = (0 << 1) + 1;
  int dist[1 << (n + 1)];
  int visited[1 << (n + 1)];
  for (int i = 0; i < (1 << (n + 1)); ++i) {
    dist[i] = INT_MAX;
    visited[i] = 0;
  }

  typedef priority_queue<int, vector<int>, Comparator> my_queue;
  my_queue frontier {Comparator(dist)};
  dist[src] = 0;
  frontier.push(src);

  while (!frontier.empty()) {
    int node = frontier.top();
    frontier.pop();
    if (visited[node])
      continue;
    if (node == target)
      break;
    visited[node] = 1;
    for (const auto& edge : adj_list[node]) {
      int neighbor = edge.first;
      int length = edge.second;
      if (!visited[neighbor]) {
        if (dist[neighbor] > dist[node] + length) {
          dist[neighbor] = dist[node] + length;
          frontier.push(neighbor);
        }
      }
    }
  }

  cout << dist[target] << endl;
}
