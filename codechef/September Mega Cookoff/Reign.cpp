//
//  Reign.cpp
//  
//
//  Created by Hieu Le on 9/20/15.
//
//

#include <stdio.h>
#include <algorithm>

using namespace std;

struct Castle {
    int a, b;
};

bool castleCmp(Castle x, Castle y) { return x.b > y.b; }

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int n;
        scanf("%d", &n);
        Castle c[n + 1];
        for (int i = 1; i <= n; i++) {
            scanf("%d", &c[i].a);
        }
        for (int i = 1; i <= n; i++) {
            scanf("%d", &c[i].b);
        }
        sort(c, c + n + 1, castleCmp);
        int dp[n + 1][n + 1];
        for (int i = 0; i <= n; i++) for (int j = 0; j <= n; j++) dp[i][j] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - 1] + c[i].a + c[i].b * (j - 1));
            }
        }
        for (int i = 1; i <= n; i++) {
            printf("%d ", dp[n][i]);
        }
        printf("\n");
    }
}

