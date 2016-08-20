#include <cstdio>
#include <vector>
#include <climits>
#include <cstdint>

const int MAX = 1e6;
int64_t n, dist[MAX + 1], total;
int start, end;

int64_t GetDistance(int a, int b) {
  if (a > b) std::swap(a, b);
  if (b == n + 1)
    return total - dist[a];
  return dist[b] - dist[a];
}
std::vector<int64_t> GetPath(int a, int b) {
  int distance = GetDistance(a, b);
  return {distance, total - distance};
}

void Solve() {
  scanf("%lld", &n);

  dist[1] = 0;
  int64_t length;
  for (int i = 1; i < n; ++i) {
    scanf("%lld", &length);
    dist[i + 1] = dist[i] + length;
  }
  scanf("%lld", &length);
  total = dist[n] + length;
    
  scanf("%d%d", &start, &end);
  int64_t result = INT64_MAX;
  for (int i = 1; i <= n; ++i) {
    std::vector<int64_t> low = GetPath(start, i);
    std::vector<int64_t> high = GetPath(i, end);
    for (int64_t l : low) {
      for (int64_t h : high)
	result = std::min(result, l + h);
    }
  }

  int64_t distance = GetDistance(start, end);
  {  
    int64_t max = 0, cur = 0;
    for (int i = start; i < end; ++i) {
      int64_t edge = GetDistance(i, i + 1);
      if (cur + edge < 0) {
	cur = 0;
      } else {
	cur += edge;
	max = std::max(max, cur);
      }
    }
    max = std::max(max, cur);
    result = std::min(result, (total - distance) + 2 * (distance - max));
  }

  {
    int64_t max = 0, cur = 0;
    for (int i = end; i != start; i = (i + 1) % n) {
      if (i == 0) i = n;
      int64_t edge = GetDistance(i, i + 1);
      if (cur + edge < 0) {
	cur = 0;
      } else {
	cur += edge;
	max = std::max(max, cur);
      }
    }
    result = std::min(result, distance + 2 * (total - distance - max));
  }

  printf("%lld\n", result);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; ++i)
    Solve();
}
