#include <cstdio>
#include <deque>

using namespace std;

int main() {
  int n;
  char employees[200000];
  scanf("%d %s", &n, employees);

  deque<int> republicans, democrats;
  for (int i = 0; i < n; ++i) {
    if (employees[i] == 'R')
      republicans.push_back(i);
    else
      democrats.push_back(i);
  }

  bool alive[n];
  for (int i = 0; i < n; ++i)
    alive[i] = true;

  int turn = 0;
  while (!republicans.empty() && !democrats.empty()) {
    while (!alive[turn]) {
      turn = (turn + 1) % n;
    }
    if (republicans.front() == turn) {
      republicans.push_back(republicans.front());
      republicans.pop_front();
      alive[democrats.front()] = false;
      democrats.pop_front();
    } else {
      democrats.push_back(democrats.front());
      democrats.pop_front();
      alive[republicans.front()] = false;
      republicans.pop_front();
    }
    turn = (turn + 1) % n;  // Advance.
  }

  if (republicans.empty())
    printf("D\n");
  else
    printf("R\n");

  return 0;
}
