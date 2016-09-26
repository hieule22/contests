#include <cstring>
#include <iostream>

using namespace std;

const int MAX_COLOR = 100;

int main() {
  int color_frequency[MAX_COLOR + 1];
  memset(color_frequency, 0, sizeof(color_frequency));
  int n, color;
  cin >> n;
  for (int i = 0; i < n; ++i) {
    cin >> color;
    color_frequency[color]++;
  }

  int result = 0;
  for (int i = 1; i <= MAX_COLOR; ++i) {
    result += (color_frequency[i] >> 1);
  }

  cout << result << endl;
  return 0;
}
