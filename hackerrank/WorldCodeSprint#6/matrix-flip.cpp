#include <iostream>
#include <cstdint>

using namespace std;

const int N = 128;
int mat[N * 2][N * 2], n, q;

int main() {
  ios_base::sync_with_stdio(0);
  cin >> q;
  for (int qq = 0; qq < q; ++qq) {
    cin >> n;
    for (int i = 0; i < 2 * n; ++i)
      for (int j = 0; j < 2 * n; ++j)
        cin >> mat[i][j];

    int width = 2 * n;
    uint64_t result = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        int element = max(mat[i][j], mat[width - 1 - i][j]);
        element = max(element, mat[width - 1 - i][width - 1 - j]);
        element = max(element, mat[i][width - 1 - j]);
        result += element;
      }
    }

    cout << result << endl;
  }
}
