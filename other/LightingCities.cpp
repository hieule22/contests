//
//  LightingCities.cpp
//  
//
//  Created by Hieu Le on 9/23/15.
//
//

#include <stdio.h>
#include <vector>
#include <algorithm>

using namespace std;

int count(int &city, vector<vector<int> > adj, int* hasLight, int* visited, bool value) {
    int res = 0;
    bool newValue;
    if (hasLight[city]) newValue = true;
    else if (!value) {
        res ++;
        newValue = true;
    } else {
        newValue = false;
    }
    vector<int>::iterator iter;
    *(visited + city) = 1;
    for (iter = adj[city].begin(); iter != adj[city].end(); iter++) {
        if (*(visited + *iter)) continue;
        res += count(*iter, adj, hasLight, visited, newValue);
    }
    return res;
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int n;
        scanf("%d", &n);
        int hasLight[n + 1];
        for (int i = 1; i <= n; i++) {
            scanf("%d", hasLight + i);
        }
        vector<vector<int> > adj(n + 1);
        for (int i = 0; i < n - 1; i++) {
            int x, y;
            scanf("%d %d", &x, &y);
            adj[x].push_back(y);
            adj[y].push_back(x);
        }
        
        int res = 500000;
        int visited[n + 1];
        for (int i = 1; i <= n; i++) {
            visited[i] = 0;
        }
        visited[1] = 1;
        // Case 1: Light in installed in city 1
        int cnt = hasLight[1] ? 1 : 0;
        vector<int>::iterator iter;
        for (iter = adj[1].begin(); iter != adj[1].end(); iter++) {
            cnt += count(*iter, adj, hasLight, visited, true);
        }
        res = min(res, cnt);
        
        // Case 2: Light is not installed in city 1
        if (!hasLight[1]) {
            cnt = 1;
            for (iter = adj[1].begin(); iter != adj[1].end(); iter++) {
                cnt += count(*iter, adj, hasLight, visited, false);
            }
            res = min(res, cnt);
        }
        
        printf("%d\n", res);
    }
}