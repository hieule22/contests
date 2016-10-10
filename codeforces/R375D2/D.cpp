#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const char WATER = '.';
const char LAND = '*';

int n, m, k;
vector<string> grid;
bool visited[50][50];

const int dr[] = {-1, 0, 1, 0};
const int dc[] = {0, -1, 0, 1};

struct Lake {
  Lake(int r, int c, int area)
      : r_(r), c_(c), area_(area) {}
  int r_, c_;
  int area_;
};

struct LakeComparator {
  bool operator() (const Lake& lhs, const Lake& rhs) const {
    return lhs.area_ < rhs.area_;
  }
};

void dfs(int row, int col, int* area, bool* is_lake) {
  if (row < 0 || row == n || col < 0 || col == m)
    return;
  if (visited[row][col] || grid[row][col] == LAND)
    return;
  (*area)++;
  visited[row][col] = true;
  if (row == 0 || row == n - 1 || col == 0 || col == m - 1)
    (*is_lake) = false;
  for (int i = 0; i < 4; ++i) {
    dfs(row + dr[i], col + dc[i], area, is_lake);
  }
}

void fill(int row, int col) {
  if (row < 0 || row == n || col < 0 || col == m)
    return;
  if (grid[row][col] == LAND)
    return;
  grid[row][col] = LAND;
  
  for (int i = 0; i < 4; ++i) {
    fill(row + dr[i], col + dc[i]);
  }
}

int main() {
  cin >> n >> m >> k;
  string line;
  for (int i = 0; i < n; ++i) {
    cin >> line;
    grid.push_back(move(line));
  }

  for (int i = 0; i < n; ++i)
    for (int j = 0; j < m; ++j)
      visited[i][j] = false;

  vector<Lake> lakes;
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      if (!visited[i][j] && grid[i][j] == WATER) {
        int area = 0;
        bool is_lake = true;
        dfs(i, j, &area, &is_lake);
        if (is_lake)
          lakes.push_back(Lake(i, j, area));
      }
    }
  }

  sort(lakes.begin(), lakes.end(), LakeComparator());
  int res = 0;
  for (size_t i = 0; i < lakes.size() - k; ++i) {
    res += lakes[i].area_;
    fill(lakes[i].r_, lakes[i].c_);
  }

  cout << res << endl;
  for (int i = 0; i < n; ++i)
    cout << grid[i] << endl;
}
