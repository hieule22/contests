#include <unordered_map>
#include <iostream>
#include <algorithm>
#include <vector>
 
using namespace std;
 
int n, m;
int x;
 
struct Matrix {
  Matrix(int n) : n(n) {
    data.resize(n);
    for (int i = 0; i < n; ++i)
      data[i].resize(n);
  }
 
  int n;
  vector<vector<int>> data;
};

Matrix temp(500);

void Multiply(Matrix* res, const Matrix& mat) {
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < n; ++j) {
      int value = 0;
      for (int k = 0; k < n; ++k) {
	if (res->data[i][k] && mat.data[k][j]) {
	  value = 1;
	  break;
	}
      }
      temp.data[i][j] = value;
    }
  }
  for (int i = 0; i < n; ++i)
    for (int j = 0; j < n; ++j)
      res->data[i][j] = temp.data[i][j];
}
 
Matrix Power(const Matrix& mat, int k) {
  static unordered_map<int, Matrix> cache;
  if (k == 1)
    return mat;
  auto iter = cache.find(k);
  if (iter != cache.end())
    return iter->second;
  Matrix result = Power(mat, k >> 1);
  Multiply(&result, result);
  if (k & 1)
    Multiply(&result, mat);
  auto res = cache.emplace(k, std::move(result));
  cerr << k << endl;
  return res.first->second;
}
 
void Solve(const Matrix& mat, int x, int k) {
  if (k == 0) {
    cout << 1 << endl << x + 1 << endl;
    return;
  }
  Matrix result = Power(mat, k);
  vector<int> friends;
  for (int i = 0; i < n; ++i) {
    if (result.data[x][i])
      friends.push_back(i);
  }
   
  cout << friends.size() << endl;
  for (int f : friends)
    cout << f + 1 << " ";
  if (friends.empty())
    cout << -1;
  cout << endl;
}
 
int main() {
  ios_base::sync_with_stdio(0);
  cin >> n;
  Matrix mat(n);
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < n; ++j)
      cin >> mat.data[i][j];
  }
 
  cin >> m;
  int x, k;
  for (int i = 0; i < m; ++i) {
    cin >> k >> x;
    Solve(mat, x - 1, k);
  } 
} 
