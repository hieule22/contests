#include <iostream>

using namespace std;
int a[1000001], n;

int main() {
  int t;
  cin >> t;
  for (int ii = 0; ii < t; ++ii) {
    cin >> n;
    a[0] = 0;
    int minIndex[2] = {0, n + 1};
    int maxIndex[2] = {0, 0};
    for (int i = 1; i <= n; ++i) {
      cin >> a[i];
      a[i] = (a[i - 1] + a[i]) % 2;
      minIndex[a[i]] = min(minIndex[a[i]], i);
      maxIndex[a[i]] = max(maxIndex[a[i]], i);
    }
    int result = 0;
    for (int i = 0; i < 2; ++i) {
      result = max(result, maxIndex[i] - minIndex[i]);
    }
    cout << result << endl;
  }
}
	       
    
    
