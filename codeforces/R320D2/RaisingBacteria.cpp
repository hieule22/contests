//
//  RaisingBacteria.cpp
//  
//
//  Created by Hieu Le on 9/16/15.
//
//

#include <stdio.h>

using namespace std;

int main() {
    int n;
    scanf("%d", &n);
    int cnt = 0;
    while (n) {
        n -= (n & -n);
        cnt ++;
    }
    printf("%d\n", cnt);
}