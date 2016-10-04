#include <iostream>
#include <bitset>
#include <unordered_map>
#include <vector>

using namespace std;

const int M = 1001;
int n, m, q;

struct Query {
  int command, i, j, k;
};

struct State {
  vector<int> levels;
  vector<pair<int, Query> > moves;
};

void ReadQuery(istream& in, Query* query) {
  in >> query->command;
  if (query->command <= 2)
    in >> query->i >> query->j;
  else if (query->command == 3)
    in >> query->i;
  else
    in >> query->k;
}

int CountSetBits(const bitset<M>& bs) {
  int result = bs.count();
  if (bs.test(M - 1))
    result -= (M - m);
  return result;
}

void DFS(unordered_map<int, State>& states, int node, int count,
         vector<bitset<M> >& shelves, unordered_map<int, int>& result) {
  for (int level : states[node].levels)
    result[level] = count;
  
  for (const auto& move : states[node].moves) {
    int next = move.first;
    const Query& query = move.second;
    if (result.find(next) != result.end())
      continue;
    if (query.command == 1) {
      bool temp = shelves[query.i].test(query.j - 1);
      if (temp) {
        DFS(states, next, count, shelves, result);
      } else {
        shelves[query.i].set(query.j - 1, true);
        DFS(states, next, count + 1, shelves, result);
      }
      shelves[query.i].set(query.j - 1, temp);
    } else if (query.command == 2) {
      bool temp = shelves[query.i].test(query.j - 1);
      if (temp) {
        shelves[query.i].set(query.j - 1, false);
        DFS(states, next, count - 1, shelves, result);
      } else {
        DFS(states, next, count, shelves, result);
      }
      shelves[query.i].set(query.j - 1, temp);
    } else if (query.command == 3) {
      int bit_count = CountSetBits(shelves[query.k]);
      shelves[query.k].flip();
      DFS(states, next, count - (bit_count) + (m - bit_count), shelves, result);
      shelves[query.k].flip();
    }
  }
}

int main() {
  cin >> n >> m >> q;
  unordered_map<int, State> states;
  states[0].levels.push_back(0);

  State* cur = &states[0];
  for (int qq = 1; qq <= q; ++qq) {
    Query query;
    ReadQuery(cin, &query);
    if (query.command <= 3) {
      cur->moves.push_back(make_pair(qq, std::move(query)));
      states[qq].levels.push_back(qq);
      cur = &states[qq];
    } else {
      int next = query.k;
      cur->moves.push_back(make_pair(qq, std::move(query)));
      states[next].levels.push_back(qq);
      cur = &states[next];
    }
  }

  vector<bitset<M> > shelves;
  for (int i = 0; i <= n; ++i)
    shelves.push_back(bitset<M>());
  unordered_map<int, int> result;
  DFS(states, 0, 0, shelves, result);
  for (int i = 1; i <= q; ++i)
    cout << result[i] << endl;
}
