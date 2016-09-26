#include <algorithm>
#include <iostream>
#include <unordered_map>
#include <vector>
#include <cstdint>

using namespace std;

unordered_map<int, vector<pair<int, int>>> rows;
uint64_t n, m;

struct PairComparator {
  bool operator() (const pair<int, int>& lhs, const pair<int, int>& rhs) const {
    return lhs.first < rhs.first;
  }
};

uint64_t Analyze(vector<pair<int, int>>& row) {
  sort(row.begin(), row.end(), PairComparator{});
  vector<pair<int, int>> partitions;
  for (const auto& route : row) {
    if (partitions.empty() || partitions.back().second < route.first) {
      partitions.push_back(route);
    } else {
      auto& last = partitions.back();
      last.second = max(last.second, route.second);
    }
  }

  uint64_t result = 0;
  for (const auto& partition : partitions) {
    result += partition.second - partition.first + 1;
  }
  return result;
}

int main() {
  int k;
  cin >> n >> m >> k;
  int r, c1, c2;
  for (int i = 0; i < k; ++i) {
    cin >> r >> c1 >> c2;
    rows[r].push_back({c1, c2});
  }

  uint64_t result = 0;
  for (auto& entry : rows) {
    result += m - Analyze(entry.second);
  }
  
  result += m * (n - rows.size());
  cout << result << endl;
  return 0;
}









