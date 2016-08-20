#include <algorithm>
#include <iostream>
#include <string>

using namespace std;

const char color[] = {'C', 'M', 'Y'};

int main() {
  ios_base::sync_with_stdio(0);
  bool isBw = false, isColor = false;
  int n, m;
  cin >> n >> m;
  char c;
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < m; ++j) {
      cin >> c;
      if (find(begin(color), end(color), c) != std::end(color)) {
        isColor = true;
        isBw = false;
      } else if (!isColor) {
        isBw = true;
      }
    }
  }
  if (isColor)
    cout << "#Color" << endl;
  else
    cout << "#Black&White" << endl;
  
  return 0;
}
