#include <iostream>
#include <vector>

using namespace std;
int n, m, x;
int mat[500][500];
int data[500][500];
int temp[500][500];

void Multiply(int param[500][500]) {
  for (int i = 0; i < n; ++i)
    memcpy(temp[i], param[i], n * sizeof(param[i][0]));
  for (int i = 0; i < n; ++i) {
    int value = 0;
    for (int j = 0; j < n; ++j) {
      if (data[x][j] && param[j][i]) {
	value = 1;
	break;
      }
    }
    temp[x][i] = value;
  }
  for (int i = 0; i < n; ++i)
    memcpy(data[i], temp[i], n * sizeof(temp[i][0]));
}

void Power(int k) {
  if (k == 1) {
    for (int i = 0; i < n; ++i)
      memcpy(data[i], mat[i], n * sizeof(mat[i][0]));
    return;
  }
  Power(k >> 1);
  Multiply(data);
  if (k & 1)
    Multiply(mat);
}

void Solve(int k) {
  if (k == 0) {
    cout << 1 << endl << x + 1 << endl;
    return;
  }
  Power(k);
  vector<int> friends;
  for (int i = 0; i < n; ++i)
    if (data[x][i])
      friends.push_back(i);

  cout << friends.size() << endl;
  for (int fr : friends)
    cout << fr + 1 << " ";
  if (friends.empty())
    cout << -1;
  cout << endl;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin >> n;
  for (int i = 0; i < n; ++i)
    for (int j = 0; j < n; ++j)
      cin >> mat[i][j];

  cin >> m;
  int k;
  for (int i = 0; i < m; ++i) {
    cin >> k >> x;
    --x;
    Solve(k);
  }
}
