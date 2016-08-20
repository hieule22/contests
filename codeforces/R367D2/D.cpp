#include <iostream>

using namespace std;

struct Node {
  Node() : data(0), left(nullptr), right(nullptr), up(nullptr), down(nullptr) {}
  int data;
  Node *left, *right, *up, *down;
};

Node mat[1002][1002];
int n, m, q;

Node* GetNode(int r, int c) {
  Node* iter = &mat[0][0];
  for (int i = 0; i < r; ++i) {
    iter = iter->down;
  }
  for (int i = 0; i < c; ++i) {
    iter = iter->right;
  }
  return iter;
}

void Print() {
  for (int i = 1; i <= n; ++i) {
    Node* iter = GetNode(i, 1);
    for (int j = 0; j < m; ++j) {
      cout << iter->data << " ";
      iter = iter->right;
    }
    cout << endl;
  }
}

int main() {
  cin >> n >> m >> q;
  for (int i = 1; i <= n; ++i) {
    for (int j = 1; j <= m; ++j) {
      cin >> mat[i][j].data;
    }
  }

  for (int i = 0; i <= n + 1; ++i) {
    for (int j = 0; j <= m + 1; ++j) {
      if (i + 1 <= n + 1)
        mat[i][j].down = &mat[i + 1][j];
      if (i - 1 >= 0)
        mat[i][j].up = &mat[i - 1][j];
      if (j + 1 <= m + 1)
        mat[i][j].right = &mat[i][j + 1];
      if (j - 1 >= 0)
        mat[i][j].left = &mat[i][j - 1];
    }
  }

  Print();
  int a, b, c, d, h, w;
  for (int t = 0; t < q; ++t) {
    cin >> a >> b >> c >> d >> h >> w;
    Node* first = GetNode(a, b);
    Node* second = GetNode(c, d);
    // Move right
    for (int i = 0; i < w - 1; ++i) {
      swap(first->up->down, second->up->down);
      swap(first->up, second->up);
      first = first->right;
      second = second->right;
    }
    swap(first->up->down, second->up->down);
    swap(first->up, second->up);
    // Move down
    for (int i = 0; i < h - 1; ++i) {
      swap(first->right->left, second->right->left);
      swap(first->right, second->right);
      first = first->down;
      second = second->down;
    }
    swap(first->right->left, second->right->left);
    swap(first->right, second->right);
    // Move left
    for (int i = 0; i < w - 1; ++i) {
      swap(first->down->up, second->down->up);
      swap(first->down, second->down);
      first = first->left;
      second = second->left;
    }
    swap(first->down->up, second->down->up);
    swap(first->down, second->down);
    // Move up
    for (int i = 0; i < h - 1; ++i) {
      swap(first->right->left, second->right->left);
      swap(first->left, second->left);
      first = first->up;
      second = second->up;
    }
    swap(first->right->left, second->right->left);
    swap(first->left, second->left);
  }

  // Prints out result
  Print();
}

