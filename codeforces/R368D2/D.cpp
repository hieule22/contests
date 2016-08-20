#include <iostream>
#include <bitset>
#include <vector>

using namespace std;
struct Interval {
  Interval(int b, int e) : begin(b), end(e) {}
  int begin, end;
  bitset<1001> state;
};

int n, m, q;

int CountBit(const bitset<1001>& state) {
  int res = 0;
  for (int i = 0; i < m; ++i)
    if (state.test(i))
      res++;
  return res;
}

int main() {
  cin >> n >> m >> q;
  vector<vector<Interval> > shelf;
  for (int i = 0; i <= n; ++i) {
    shelf.push_back({Interval(0, 0)});
  }

  int command, i, j, k;
  int total = 0;
  for (int qq = 1; qq <= q; ++qq) {
    cin >> command;
    if (command == 1) {
      cin >> i >> j;
      auto& cur = *shelf[i].rbegin();
      cur.end = qq - 1;
      bitset<1001> state(cur.state);
      if (!state.test(j - 1)) {
        total++;
        state.set(j - 1, true);
      }
      shelf[i].push_back(Interval(qq, qq));
      (*shelf[i].rbegin()).state = std::move(state);
      cout << total << endl;
    } else if (command == 2) {
      cin >> i >> j;
      auto& cur = *shelf[i].rbegin();
      cur.end = qq - 1;
      bitset<1001> state(cur.state);
      if (state.test(j - 1)) {
        total--;
        state.set(j - 1, false);
      }
      shelf[i].push_back(Interval(qq, qq));
      (*shelf[i].rbegin()).state = std::move(state);
      cout << total << endl;
    } else if (command == 3) {
      cin >> i;
      auto& cur = *shelf[i].rbegin();
      cur.end = qq - 1;
      bitset<1001> state(cur.state);
      total -= CountBit(state);
      state.flip();
      total += CountBit(state);
      shelf[i].push_back(Interval(qq, qq));
      (*shelf[i].rbegin()).state = std::move(state);
      cout << total << endl;
    } else if (command == 4) {
      cin >> k;
      total = 0;
      for (int ii = 1; ii <= n; ++ii) {
        (*shelf[ii].rbegin()).end = qq - 1;
        int low = 0, high = shelf[ii].size() - 1, mid;
        while (low <= high) {
          mid = low + ((high - low) / 2);
          if (shelf[ii][mid].begin <= k && k <= shelf[ii][mid].end)
            break;
          else if (shelf[ii][mid].begin > k)
            high = mid - 1;
          else
            low = mid + 1;
        }
        Interval cur(qq, qq);
        cur.state = shelf[ii][mid].state;
        total += CountBit(cur.state);
        shelf[ii].push_back(std::move(cur));
      }
      cout << total << endl;
    }
  }
}
