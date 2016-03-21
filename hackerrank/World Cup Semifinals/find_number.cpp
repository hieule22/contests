//
//  find_number.cpp
//  
//
//  Created by Hieu Le on 9/20/15.
//
//

#include <stdio.h>
#include <unordered_map>
#include <algorithm>
#include <assert.h>
#include <limits.h>

using namespace std;

int a,b,c;
unordered_map<int,int> map;

int compute(int);

int maxify(int left, int mid, int right) {
    int res = 0;
    if (left) res = max(res, a + compute(left));
    if (mid) res = max(res, b + compute(mid));
    if (right) res = max(res, c + compute(right));
    assert (res);
    return res;
}

int compute(int n) {
    if (n == 1) return 0;
    if (map.find(n) != map.end()) return map[n];
    int res = INT_MAX;
    for (int left = 0; left < n; left++) {
        for (int mid = 0; mid <= n - left; mid++) {
            int right = n - left - mid;
            if (!left && !mid) continue;
            if (!left && !right) continue;
            if (!mid && !right) continue;
            res = min(res, maxify(left, mid, right));
        }
    }
    assert (res != INT_MAX);
    map[n] = res;
    return res;
}

int main() {
    int t;
    scanf("%d\n", &t);
    while (t--) {
        int n;
        scanf("%d %d %d %d", &n, &a, &b, &c);
        map.clear();
        printf("%d\n", compute(n));
    }
}
