//
//  KefaAndDishes.cpp
//  
//
//  Created by Hieu Le on 9/22/15.
//
//

#include <stdio.h>
#include <algorithm>

using namespace std;

typedef long long int int_64;

int main() {
    int n, m, k;
    scanf("%d %d %d", &n, &m, &k);
    int dish[n];
    for (int i = 0; i < n; i++) {
        scanf("%d", dish + i);
    }
    
    int extra[n][n];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            extra[i][j] = 0;
        }
    }
    for (int i = 0; i < k; i++) {
        int x, y, increase;
        scanf("%d %d %d", &x, &y, &increase);
        extra[x - 1][y - 1] = increase;
    }
    
    int_64 dp[1 << n][n];
    int_64 res = 0;
    for (int mask = 1; mask < (1 << n); mask++) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!(mask & (1 << i))) continue;
            cnt++;
            int remain = mask - (1 << i);
            dp[mask][i] = 0;
            for (int j = 0; j < n; j++) {
                if (!(remain & (1 << j))) continue;
                dp[mask][i] = max(dp[mask][i], dp[remain][j] + extra[j][i]);
            }
            dp[mask][i] += dish[i];
        }
        if (cnt == m) {
            for (int i = 0; i < n; i++)
                if (mask & (1 << i)) res = max(res, dp[mask][i]);
        }
    }
    printf("%I64d\n", res);
}