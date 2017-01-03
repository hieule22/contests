#include <vector>
#include <cstdio>
using namespace std;

#define MAXN 200000
int n, q, k, l;
vector<vector<int>> indices;
int owners[MAXN], sizes[MAXN];

int CountInstances(int owner, int threshold) {
  // Threshold greater than all bids of this owner.
  if (indices[owner].empty() || threshold > *indices[owner].rbegin())
    return 0;
  // Find first bid of this owner not exceeding threshold.
  int low = 0, high = indices[owner].size() - 1;
  while (low < high) {
    int mid = low + (high - low) / 2;
    if (indices[owner][mid] >= threshold)
      high = mid;
    else
      low = mid + 1;
  }
  return indices[owner].size() - low;
}

int CountInstances(const vector<int>& absentees, int threshold) {
  int result = 0;
  for (int absentee : absentees) {
    result += CountInstances(absentee, threshold);
  }
  return result;
}

void Solve(const vector<int>& absentees) {
  int low = 0, high = n - 1;
  while (low < high) {
    int mid = low + (high - low + 1) / 2;
    int cnt = CountInstances(absentees, mid);
    if (n - mid > cnt)
      low = mid;
    else
      high = mid - 1;
  }

  if (CountInstances(absentees, low) == n) {
    printf("0 0\n");
    return;
  }

  int winner = owners[low];
  low = 0; high = n - 1;
  while (low < high) {
    int mid = low + (high - low + 1) / 2;
    int cnt = CountInstances(absentees, mid) + CountInstances(winner, mid);
    if (n - mid > cnt)
      low = mid;
    else
      high = mid - 1;
  }

  int temp = CountInstances(winner, low), index;
  if (CountInstances(absentees, low) + temp == n) {
    index = *indices[winner].begin();
  } else {
    index = *(indices[winner].end() - temp);
  }
  printf("%d %d\n", winner, sizes[index]);
}

int main() {
  scanf("%d", &n);
  indices.resize(n + 1);
  for (int i = 0; i < n; ++i) {
    scanf("%d %d", &owners[i], &sizes[i]);
    indices[owners[i]].push_back(i);
  }

  scanf("%d", &q);
  for (int qq = 0; qq < q; ++qq) {
    vector<int> absentees;
    scanf("%d", &k);
    for (int i = 0; i < k; ++i) {
      scanf("%d", &l);
      absentees.push_back(l);
    }
    Solve(absentees);
  }

  return 0;
}
