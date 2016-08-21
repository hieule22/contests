//
//  ORGame.cpp
//  
//
//  Created by Hieu Le on 9/16/15.
//
//

#include <stdio.h>
#include <algorithm>

using namespace std;

long pow(int x, int e) {
    if (e == 0 || x == 1) return 1;
    long res = pow(x, e >> 1);
    res *= res;
    if (e & 1) res *= x;
    return res;
}

int main() {
    int n, k, x;
    scanf("%d %d %d", &n, &k, &x);
    int a[n + 2];
    for (int i = 1; i <= n; i++) scanf("%d", a + i);
    
    int prefix[n + 2], suffix[n + 2];
    prefix[0] = suffix[n + 1] = 0;
    for (int i = 1; i <= n; i++) prefix[i] = a[i] | prefix[i - 1];
    for (int i = n; i >= 1; i--) suffix[i] = a[i] | suffix[i + 1];
    
    long factor = pow(x, k);
    long res = 0;
    for (int i = 1; i <= n; i++)
        res = max(res, (prefix[i - 1] | (a[i] * factor)) | suffix[i + 1]);
    
    printf("%ld\n", res);
}