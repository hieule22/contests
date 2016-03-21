//
//  SameModulo.cpp
//  
//
//  Created by Hieu Le on 9/19/15.
//
//

#include <stdio.h>
#include <algorithm>
#include <cmath>
#include <set>
#define MAX 1000000000
using namespace std;

int findGcd(int a, int b) {
    while (b != 0) {
        int temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}

int main() {
    int n;
    scanf("%d", &n);
    int a[n];
    int minimum = MAX;
    for (int i = 0; i < n; i++) {
        scanf("%d", a + i);
        minimum = min(minimum, a[i]);
    }
    
    int delta[n];
    int gcd = 0;
    for (int i = 0; i < n; i++) {
        delta[i] = a[i] - minimum;
        gcd = findGcd(gcd, delta[i]);
    }
    
    set<int> s;
    s.insert(gcd);
    int square = (int)sqrt(gcd);
    for (int i = 2; i <= square; i++) {
        if (gcd % i == 0) {
            s.insert(i);
            s.insert(gcd / i);
        }
    }
    set<int>::iterator it;
    for (it = s.begin(); it != s.end(); it++) {
        printf("%d ", *it);
    }
}

