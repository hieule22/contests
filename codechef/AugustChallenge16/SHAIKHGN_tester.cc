#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

const int MAX = 1e9;
int mat[500][500];

int main(int argc, char** argv){
  int n = atoi(argv[1]);
  srand(time(NULL));
  cout << n << endl;
  for (int i = 0; i < n; ++i) { 
    for (int j = 0; j < n; ++j) {
      mat[i][j] = rand() % 2;
      cout << mat[i][j] << " ";
    }
    cout << endl;
  }

  int x, k;
  cout << n << endl;
  for (int i = 0; i < n; ++i) {
    k = rand() % MAX;
    x = rand() % n + 1;
    cout << k << " " << x << endl;
  }
}
 
