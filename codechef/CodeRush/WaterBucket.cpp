//
//  WaterBucket.cpp
//  
//
//  Created by Hieu Le on 9/18/15.
//
//

#include <stdio.h>
#include <vector>

typedef long long int int_64;
using namespace std;

int_64 getSumSquares(int_64 &n) {
    int_64 res = 2 * n * n * n + 3 * n * n + n;
    res /= 6;
    return res;
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int_64 s;
        scanf("%lld", &s);
        int_64 low = 1;
        int_64 high = (int_64)1e6;
        while (low != high) {
            int_64 mid = low + ((high - low + 1) >> 1);
            int_64 sum = getSumSquares(mid);
            if (sum == s) {
                low = mid;
                break;
            } else if (sum < s) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        printf("%lld\n", low);
    }
}

