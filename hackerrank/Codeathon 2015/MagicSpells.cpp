//
//  MagicSpells.cpp
//  
//
//  Created by Hieu Le on 9/26/15.
//
//

#include <stdio.h>
#include <unordered_set>
#include <algorithm>

using namespace std;

unordered_set<int> next[10001];
unordered_set<int> prev[10001];

int getMinElement(unordered_set<int> set) {
    for (unordered_set<int>::iterator iter = set.begin(); iter != set.end(); iter++) {
        int element = *iter;
        if (next[element].empty()) {
            set.remove(element);
            return element;
        }
    }
    return -1;
}

int solve() {
    int n, m, k;
    scanf("%d %d %d", &n, &m, &k);
    int cost[n + 1];
    for (int i = 0; i < n; i++) {
        scanf("%d ", cost + i);
    }
    cost[n] = -10000;
    
    for (int i = 0; i < n - 1; i++) {
        int x, y;
        scanf("%d %d", &x, &y);
        next[x].push_back(y);
        prev[x].push_back(x);
    }
    
    for (int i = 0; i < n; i++) {
        next[i].push_back(n);
        prev[n].push_back(i);
    }
    
    int dp[n + 1][k + 1];
    for (int i = 0; i <= n; i++) {
        dp[i][0] = cost[i];
        for (int j = 1; j <= k; j++) {
            dp[i][j] = max(cost[i], cost[i] * m);
        }
    }
    
    unordered_set<int> remain;
    for (int i = 0; i <= n; i++) {
        set.push_back(i);
    }
    
    while (!set.empty()) {
        int leaf = getMinElement(set);
        for (unordered_set<int>::iterator iter = prev[leaf].begin(); iter != prev[leaf].end(); iter++) {
            int node = *iter;
            next[node].remove(leaf);
            dp[node][0] = max(dp[node][0], cost[node] + dp[leaf][0]);
            for (int i = 0; i <= k; i++) {
                dp[node][i] = max(dp[node][i], cost[node] + dp[leaf][i]);
                dp[node][i] = max(dp[node][i], cost[node] * m + dp[leaf][i - 1]);
            }
        }
    }
    
    int res = -10000;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= k; j++) {
            res = max(res, dp[i][j]);
        }
    }
    return res;
}
int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        printf("%d\n", solve());
    }
}