#include <iostream>

using namespace std;

const int N = 5;
const int M = 10;
int cur[N], target[N];

int CountTransition(int a, int b) {
  int delta = abs(a - b);
  return min(delta, M - delta);
}

int main() {
  for (int i = 0; i < N; ++i)
    cin >> cur[i];
  for (int i = 0; i < N; ++i)
    cin >> target[i];

  int res = 0;
  for (int i = 0; i < N; ++i)
    res += CountTransition(cur[i], target[i]);
  cout << res << endl;
}
