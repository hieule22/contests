//
//  KefaAndPark.cpp
//  
//
//  Created by Hieu Le on 9/22/15.
//
//

#include <stdio.h>
#include <vector>


typedef long long int int_64;

using namespace std;

vector<int> adj[100005];
int hasCat[100005];
int n, m;

int dfs(int before, int cur, int cnt) {
    if (hasCat[cur]) cnt++;
    else cnt = 0;
    if (cnt > m) return 0;
    if (cur != 1 && adj[cur].size() == 1) return 1;
    int res = 0;
    vector<int>::iterator it;
    for (it = adj[cur].begin(); it != adj[cur].end(); it++) {
        int child = *it;
        if (child != before) res += dfs(cur, child, cnt);
    }
    return res;
}

int main() {
    scanf("%d %d", &n, &m);
    for (int i = 1; i <= n; i++) {
        int token;
        scanf("%d ", &hasCat[i]);
    }
    
    for (int i = 0; i < n - 1; i++) {
        int x, y;
        scanf("%d %d", &x, &y);
        adj[x].push_back(y);
        adj[y].push_back(x);
    }
    
    int res;
    if (n == 1) res = 1;
    else res = dfs(0, 1, 0);
    printf("%d\n", res);
}
