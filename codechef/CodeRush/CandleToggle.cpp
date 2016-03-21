//
//  CandleToggle.cpp
//  
//
//  Created by Hieu Le on 9/18/15.
//
//

#include <stdio.h>
#include <cmath>

using namespace std;
typedef long long int int_64;

int main() {
    int q;
    scanf("%d\n", &q);
    while (q--) {
        int_64 l, r;
        scanf("%lld %lld", &l, &r);
        int_64 low = (int_64)sqrt(l);
        if (low * low < l) low ++;
        int_64 high = (int_64)sqrt(r);
        if (high * high > r) high --;
        int_64 cnt = (low > high) ? 0 : (high - low + 1);
        printf("%lld\n", cnt);
    }
}