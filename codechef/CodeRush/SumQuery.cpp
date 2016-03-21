//
//  SumQuery.cpp
//  
//
//  Created by Hieu Le on 9/19/15.
//
//

#include <stdio.h>

typedef long long int int_64;


int_64 getSum(int a[], int_64 sum[][], int &x, int &y) {
    if (x >= a.size()) return 0;
    if (!sum[x][y]) return sum[x][y];
    sum[x][y] = a[x] + getSum(a, sum, x + y, y);
    return sum[x][y];
}

int main() {
    int n;
    scanf("%d", &n);
    int a[n + 1];
    for (int i = 1; i <= n; i++) scanf("%d", a + i);
    
    int_64 sum[n + 1][n];
    for (int i = 0; i < n + 1; i++) {
        for (int j = 0; j < n; j++) sum[i][j] = 0;
    }
    
    int q;
    scanf("%d", &q);
    while (q--) {
        int x, y;
        scanf("%d %d", &x, &y);
        int_64 res = getSum(a, sum, x, y);
        printf("%lld\n", res);
    }
}