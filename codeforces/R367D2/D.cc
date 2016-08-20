#include <iostream>
#include <cstdint>

using namespace std;

struct Trie {
  Trie() : cnt(0) {
    child[0] = child[1] = nullptr;
  }
  
  ~Trie() {
    for (int i = 0; i < 2; ++i)
      delete child[i];
  }
  
  int cnt;
  Trie* child[2];
};

void Insert(Trie* node, uint32_t x, int level) {
  node->cnt++;
  if (level == -1)
    return;
  if (x & (1 << level)) {
    if (node->child[1] == nullptr)
      node->child[1] = new Trie();
    Insert(node->child[1], x, level - 1);
  } else {
    if (node->child[0] == nullptr)
      node->child[0] = new Trie();
    Insert(node->child[0], x, level - 1);
  }
}

void Remove(Trie* node, uint32_t x, int level) {
  node->cnt--;
  if (level == -1)
    return;
  if (x & (1 << level))
    Remove(node->child[1], x, level - 1);
  else
    Remove(node->child[0], x, level - 1);
}

void Process(Trie* node, uint64_t x, int level, uint64_t* res) {
  if (level == -1)
    return;
  if (x & (1 << level)) {
    if (node->child[0] != nullptr && node->child[0]->cnt > 0) {
      *res += (1 << level);
      Process(node->child[0], x, level - 1, res);
    } else {
      Process(node->child[1], x, level - 1, res);
    }
  } else {
    if (node->child[1] != nullptr && node->child[1]->cnt > 0) {
      *res += (1 << level);
      Process(node->child[1], x, level - 1, res);
    } else {
      Process(node->child[0], x, level - 1, res);
    }
  }
}

int main() {
  Trie root;
  Insert(&root, 0, 30);

  int q;
  uint32_t x;
  char command;
  cin >> q;
  for (int i = 0; i < q; ++i) {
    cin >> command >> x;
    if (command == '+')
      Insert(&root, x, 30);
    else if (command == '-')
      Remove(&root, x, 30);
    else {
      uint64_t res = 0;
      Process(&root, x, 30, &res);
      cout << res << endl;
    }
  }
}
