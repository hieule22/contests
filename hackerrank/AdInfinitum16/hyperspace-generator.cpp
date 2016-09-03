#include <iostream>
#include <ctime>
#include <cstdlib>
#include <cstdint>

using namespace std;

const int64_t MIN = -1e9;
const int64_t MAX = 1e9;

int main(int argc, char** argv) {
  int n = atoi(argv[1]);
  int m = atoi(argv[2]);

  cout << n << " " << m << endl;
  srand(time(NULL));
  for (int i = 0; i < n; ++i) {
    for (int j = 0; j < m; ++j)
      cout << rand() % MAX << " ";
    cout << endl;
  }
}
