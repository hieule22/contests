#include <iostream>
#include <algorithm>
#include <cmath>
#include <climits>

using namespace std;

class Structure {
 public:
  Structure(const string& s, int k) : s_(s) {
    int maxPow = (int) ceil(log10(k) / log10(2));
    tree_ = new int[s_.size()];
    ranks_ = new int*[maxPow + 1];
    for (int i = 0; i < maxPow + 1; ++i)
      ranks_[i] = new int[s_.size()];

    for (int i = 0; i < s_.size(); ++i) {
      tree_[i] = i;
      ranks_[0][i] = s[i] - 'a';
    }

    for (int i = 1; i <= maxPow; ++i) {
      auto comparator = [i, this] (int a, int b) {
        if (ranks_[i - 1][a] == ranks_[i - 1][b]) {
          a = (a + (1 << (i - 1))) % s_.size();
          b = (b + (1 << (i - 1))) % s_.size();
        }

        return ranks_[i - 1][a] < ranks_[i - 1][b];
      };
      sort(tree_, tree_ + s_.size(), comparator);

      // Update ranks
      int currentRank = ranks_[i][tree_[0]];
      for (int j = 1; j < s_.size(); ++j) {
        if (comparator(tree_[j - 1], tree_[j]))
          ++currentRank;
        ranks_[i][tree_[j]] = currentRank;
      }
    }
  }

  int getIndex(int index) {
    return tree_[index];
  }

  int getLower(const string& target) {
    int low = 0, high = s_.size() - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (compare(mid, target) >= 0)
        high = mid;
      else
        low = mid + 1;
    }
    return compare(low, target) == 0 ? low : INT32_MAX;
  }

  int getHigher(const string& target) {
    int low = 0, high = s_.size() - 1;
    while (low < high) {
      int mid = low + (high - low + 1) / 2;
      if (compare(mid, target) <= 0)
        low = mid;
      else
        high = mid - 1;
    }
    return compare(low, target) == 0 ? low : INT32_MIN;
  }

 private:
  int *tree_;
  int **ranks_;
  const std::string& s_;

  int compare(int index, const string& target) {
    int start = tree_[index];
    for (char c : target) {
      if (s_[start] != c)
        return s_[start] - c;
      start = (start + 1) % s_.size();
    }
    return 0;
  }
};

int main() {
  int n, k;
  string s;
  cin >> n >> k >> s;
  Structure tree(s, k);

  int relation[s.size()];
  for (int i = 0; i < s.size(); ++i)
    relation[i] = -1;

  int g; cin >> g;
  for (int i = 0; i < g; ++i) {
    string game;
    cin >> game;
    int low = tree.getLower(game);
    int high = tree.getHigher(game);
    for (int j = low; j <= high; ++j)
      relation[tree.getIndex(j)] = i;
  }

  for (int i = 0; i < k; ++i) {
    bool seen[g];
    for (int j = 0; j < g; ++j) seen[j] = false;

    int current = i;
    bool unique = true;
    for (int j = 0; j < n; ++j) {
      if (relation[current] < 0 || seen[relation[current]]) {
        unique = false;
        break;
      }
      seen[relation[current]] = true;
      current = (current + k) % s.size();
    }

    if (unique) {
      cout << "YES" << endl;
      int start = i;
      for (int j = 0; j < n; ++j) {
        cout << relation[start] + 1 << " ";
        start = (start + k) % s.size();
      }
      cout << endl;
      return 0;
    }
  }

  cout << "NO" << endl;
  return 0;
}
