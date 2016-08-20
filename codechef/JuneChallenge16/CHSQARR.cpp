#include <stdio.h>
#include <iostream>
#include <limits.h>
#include <time.h>
#include <unordered_map>
#define TEST
#define min(a, b) ((a < b) ? a : b)
#define max(a, b) ((a < b) ? b : a)
#pragma comment(linker, "/STACK: 2000000")
 
const int MAX = 1000;
int n, m, A[MAX][MAX];
int sum[MAX][MAX];

struct Point {
    int x, y, mx;
    Point() {}
    Point(int x, int y, int mx) : x(x), y(y), mx(mx) {}

    bool operator < (const Point& other) const {
        return mx < other.mx;
    }
};

Point T[10000000];

struct Segtree2d {
 // TODO: calculate the accurate space needed
    int n, m;

    // initialize and construct segment tree
    void init(int n, int m) {
        this -> n = n;
        this -> m = m;
        build(1, 0, 0, n - 1, m - 1);
    }

    // build a 2D segment tree from data [ (a1, b1), (a2, b2) ]
    // Time: O(n logn)
    Point build(int node, int a1, int b1, int a2, int b2) {
        // out of range
        if (a1 > a2 or b1 > b2)
            return def();

        // if it is only a single index, assign value to node
        if (a1 == a2 and b1 == b2)
            return T[node] = Point(a1, b1, A[a1][b1]);

        // split the tree into four segments
        T[node] = def();
        T[node] = maxNode(T[node], build(4 * node - 2, a1, b1, (a1 + a2) / 2, (b1 + b2) / 2 ) );
        T[node] = maxNode(T[node], build(4 * node - 1, (a1 + a2) / 2 + 1, b1, a2, (b1 + b2) / 2 ));
        T[node] = maxNode(T[node], build(4 * node + 0, a1, (b1 + b2) / 2 + 1, (a1 + a2) / 2, b2) );
        T[node] = maxNode(T[node], build(4 * node + 1, (a1 + a2) / 2 + 1, (b1 + b2) / 2 + 1, a2, b2) );
        return T[node];
    }

    // helper function for query(int, int, int, int);
    Point query(int node, int a1, int b1, int a2, int b2, int x1, int y1, int x2, int y2) {
        // if we out of range, return dummy
        if (x1 > a2 or y1 > b2 or x2 < a1 or y2 < b1 or a1 > a2 or b1 > b2)
            return def();

        // if it is within range, return the node
        if (x1 <= a1 and y1 <= b1 and a2 <= x2 and b2 <= y2)
            return T[node];

        // split into four segments
        Point mx = def();
        mx = maxNode(mx, query(4 * node - 2, a1, b1, (a1 + a2) / 2, (b1 + b2) / 2, x1, y1, x2, y2) );
        mx = maxNode(mx, query(4 * node - 1, (a1 + a2) / 2 + 1, b1, a2, (b1 + b2) / 2, x1, y1, x2, y2) );
        mx = maxNode(mx, query(4 * node + 0, a1, (b1 + b2) / 2 + 1, (a1 + a2) / 2, b2, x1, y1, x2, y2) );
        mx = maxNode(mx, query(4 * node + 1, (a1 + a2) / 2 + 1, (b1 + b2) / 2 + 1, a2, b2, x1, y1, x2, y2));

        // return the maximum value
        return mx;
    }

    // query from range [ (x1, y1), (x2, y2) ]
    // Time: O(logn)
    Point query(int x1, int y1, int x2, int y2) {
        return query(1, 0, 0, n - 1, m - 1, x1, y1, x2, y2);
    }

    // utility functions; these functions are virtual because they will be overridden in child class
    virtual Point maxNode(Point a, Point b) {
        return max(a, b);
    }

    // dummy node
    virtual Point def() {
        return Point(0, 0, INT_MIN);
    }
};
 
int GetSum(int x, int y, int a, int b)
{
  int result = sum[x + a - 1][y + b - 1];
  if (x > 0)
    result -= sum[x - 1][y + b - 1];
  if (y > 0)
    result -= sum[x + a - 1][y - 1];
  if (x > 0 && y > 0)
    result += sum[x - 1][y - 1];
  return result;
}

 
int main()
{
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      scanf("%d", &A[i][j]);
      sum[i][j] = A[i][j];
      if (i > 0)
        sum[i][j] += sum[i - 1][j];
      if (j > 0)
        sum[i][j] += sum[i][j - 1];
      if (i > 0 && j > 0)
        sum[i][j] -= sum[i - 1][j - 1];
    }
  }
 	#ifdef TEST
 	clock_t t1 = clock();
  #endif

 	Segtree2d tree;
 	tree.init(n, m);

  #ifdef TEST
  clock_t t2 = clock();
  std::cerr << double(t2 - t1) / CLOCKS_PER_SEC << std::endl;
  #endif
 
  int q, a, b;
  scanf("%d", &q);
  for (int qq = 0; qq < q; qq++) {
    scanf("%d%d", &a, &b);
 	  int res = INT_MAX;
    for (int i = 0; i < n - a + 1; i++)
      for (int j = 0; j < m - b + 1; j++) {
      	int temp = tree.query(i, j, i + a - 1, j + b - 1).mx * a * b - GetSum(i, j, a, b);
        res = min(res, temp);
      }
      #ifndef TEST
    printf("%d\n", res);
    #endif	
  }

  #ifdef TEST
  clock_t t3 = clock();
  std::cerr << double(t3 - t2) / CLOCKS_PER_SEC << std::endl;
  #endif
} 