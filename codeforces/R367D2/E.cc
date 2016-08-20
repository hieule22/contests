#include <iostream>
#include <cstdint>
#include <vector>

using namespace std;

struct Transform {
  int64_t dx = 0;
  int64_t dy = 0;
};


int mat[1001][1001], res[1001][1001];
int n, m, q;

int main() {
  cin >> n >> m >> q;
  for (int i = 1; i <= n; ++i) {
    for (int j = 1; j <= m; ++j) {
      cin >> mat[i][j];
    }
  }

  vector<vector<Transform> > t;
  for (int i = 0; i <= n + 1; ++i) {
    t.push_back(vector<Transform>());
    for (int j = 0; j <= m + 1; ++j)
      t[i].push_back(Transform());
  }
    
  int a, b, c, d, h, w;
  for (int i = 0; i < q; ++i) {
    cin >> a >> b >> c >> d >> h >> w;
    int64_t dx = c - a;
    int64_t dy = d - b;
    for (int r = a; r < a + h; ++r) {
      t[r][b].dx += dx;
      t[r][b].dy += dy;
      t[r][b + w].dx -= dx;
      t[r][b + w].dy -= dy;
    }

    for (int r = c; r < c + h; ++r) {
      t[r][d].dx -= dx;
      t[r][d].dy -= dy;
      t[r][d + w].dx += dx;
      t[r][d + w].dy += dy;
    }
  }

  for (int i = 1; i <= n; ++i) {
    for (int j = 1; j <= m; ++j) {
      t[i][j].dx += t[i][j - 1].dx;
      t[i][j].dy += t[i][j - 1].dy;
      res[i + t[i][j].dx][j + t[i][j].dy] = mat[i][j];
    }
  }

  for (int i = 1; i <= n; ++i) {
    for (int j = 1; j <= m; ++j)
      cout << res[i][j] << " ";
    cout << endl;
  }
}
