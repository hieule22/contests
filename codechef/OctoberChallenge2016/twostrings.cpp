#include <string>
#include <iostream>
#include <cstdint>

using namespace std;

const uint64_t MOD = 1e9 + 7;

uint64_t solve() {
  string a, b;
  cin >> a >> b;
  int n = a.size();

  int index = 0;
  while (index < n) {
    if (a[index] == '2' || b[index] == '2') {
      if (a[index] != '2') swap(a[index], b[index]);
      
      if (++index == n || (a[index] == '1' && b[index] == '1'))
        return 0;
      if (a[index] != '2') swap(a[index], b[index]);
      
      if (++index == n || (a[index] == '2' && b[index] == '2'))
        return 0;
      if (a[index] != '1') swap(a[index], b[index]);

      if (++index == n)
        return 0;
    } else {
      ++index;
    }
  }

  index = 0;
  while (index < n) {
    if (b[index] == '2') {
      if (++index == n || b[index] != '2')
        return 0;
      if (++index == n || b[index] != '1')
        return 0;
      if (++index == n)
        return 0;
    } else {
      ++index;
    }
  }

  index = 0;
  uint64_t res = 1;
  while (index < n) {
    if ()
  }
}

int main() {
  int test_count;
  cin >> test_count;
  for (int i = 0; i < test_count; ++i)
    cout << solve() << endl;
  return 0;
}
