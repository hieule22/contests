#include <iostream>

using namespace std;

int main() {
  int n, k, c, charged, total;
  cin >> n >> k;
  for (int i = 0; i < n; ++i) {
    cin >> c;
    if (i != k)
      total += c;
  }
  cin >> charged;

  int delta = charged - (total / 2);
  if (delta == 0)
    cout << "Bon Appetit" << endl;
  else
    cout << delta << endl;
}
