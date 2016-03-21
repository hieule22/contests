//
//  CountInversions.cpp
//  
//
//  Created by Hieu Le on 9/20/15.
//
//

#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);
    int a[n + 1];
    a[0] = 0;
    bool has_inv = false;
    for (int i = 1; i < n + 1; i++) {
        scanf("%d", a + i);
        if (a[i] < a[i - 1]) has_inv = true;
    }
    if (!has_inv) {
        printf("Cool Array\n");
        return 0;
    }
    int max_inv = 0, left = 0, right = 0;
    for (int i = 1; i < n; i++) {
        for (int j = i + 1; j <= n; j++) {
            int cnt = 0;
            for (int k = i; k <= j; k++) {
                if (a[k] > a[i]) cnt--;
                else if (a[k] < a[i]) cnt++;
                if (a[k] > a[j]) cnt++;
                else if (a[k] < a[j]) cnt--;
            }
            if (cnt > max_inv) {
                left = i;
                right = j;
                max_inv = cnt;
            }
        }
    }
    printf("%d %d\n", left, right);
}
