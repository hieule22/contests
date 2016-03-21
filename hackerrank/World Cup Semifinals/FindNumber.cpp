//
//  FindNumber.cpp
//  
//
//  Created by Hieu Le on 9/19/15.
//
//

#include <stdio.h>
#include <iostream>
#include <assert.h>
#include <unordered_map>
#include <climits>

using namespace std;

typedef long long int int_64;

int a, b, c;
int_64 n, cnt[5000];

void swap(int &x, int &y) {
    int temp = x;
    x = y;
    y = temp;
}

void solve() {
    scanf("%lld %d %d %d", &n, &a, &b, &c);
    if (a > b) swap(a,b);
    if (a > c) swap(a,c);
    if (b > c) swap(b,c);
    
    for (int i = 0; i < 5000; i++) {
        if (i < b) cnt[i] = 1;
        else {
            cnt[i] = cnt[i - a] + cnt[i - b];
            if (i >= c) cnt[i] += cnt[i - c];
        }
        if (cnt[i] >= n) {
            printf("%d\n", i);
            return;
        }
    }
}
int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        solve();
    }
    return 0;
}




