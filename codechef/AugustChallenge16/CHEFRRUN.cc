#include <unordered_map>
#include <vector>
#include <cstring>

using namespace std;

const int MAX = 1e6;
int a[MAX], state[MAX], n;

vector<int> FindMagic(int src) {
  int iter = src;
  vector<int> circle;
  do {
    circle.push_back(iter);
    state[iter] = 2;
    iter = (iter + a[iter] + 1) % n;
  } while (iter != src);
  return circle;
}

void DFS(const unordered_map<int, vector<int> >& children, int src) {
  auto iter = children.find(src);
  if (iter == children.end())
    return;
  for (int child : iter->second) {
    if (state[child] == 2) continue;
    state[child] = 1;
    DFS(children, child);
  }
}

void Solve() {
  scanf("%d", &n);
  unordered_map<int, vector<int> > children;
  for (int i = 0; i < n; ++i) {
    scanf("%d", a + i);
    int next = (i + a[i] + 1) % n;
    children[next].push_back(i);
  }
  memset(state, 0, sizeof(state[0]) * n);
  for (int i = 0; i < n; i++) {
    if (state[i]) continue;
    int iter = i;
    while (!state[iter]) {
      state[iter] = 1;
      iter = (iter + a[iter] + 1) % n;
    }

    vector<int> circle = FindMagic(iter);
    for (int box : circle) {
      DFS(children, box);
    }
  }
  int res = 0;
  for (int i = 0; i < n; i++)
    if (state[i] == 2)
      res++;
  printf("%d\n", res);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int tt = 0; tt < t; ++tt)
    Solve();
}

