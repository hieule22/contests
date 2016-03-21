//
//  SumNim.cpp
//  
//
//  Created by Hieu Le on 9/19/15.
//
//

#include <stdio.h>

using namespace std;

const int MAX = 36;
int a[MAX];
int n;

/* soFar: the xor value so far
   cur: the ongoing sum
   index: the current index
 */

int recurse(int soFar, int cur, int index) {
    if (index == n) {
        if (soFar ^ cur) return 0;
        else return 1;
    }
    return recurse(soFar ^ cur, a[index], index + 1)
    + recurse(soFar, cur + a[index], index + 1);
}

int main() {
    scanf("%d ", &n);
    for (int i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }
    int res = recurse(0, 0, 0) >> 1;
    printf("%d\n", res);
}
