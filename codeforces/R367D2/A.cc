#include <iostream>
#include <limits>
#include <cmath>

using namespace std;

int main() {
  double a, b, x, y, v;
  int n;
  cin >> a >> b >> n;
  double res = numeric_limits<double>::max();
  for (int i = 0; i < n; ++i) {
    cin >> x >> y >> v;
    double dx = a - x, dy = b - y;
    double dist = sqrt(dx * dx + dy * dy);
    res = min(res, dist / v);
  }

  printf("%.9f\n", res);
}
