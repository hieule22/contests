//
//  Polyline.cpp
//  
//
//  Created by Hieu Le on 9/16/15.
//
//

#include <stdio.h>
#include <algorithm>

using namespace std;

int main() {
    int a, b;
    scanf("%d %d", &a, &b);
    double res = -1;
    if (a == b) {
        res = a;
    } else if (a < b) {
        res = -1;
    } else {
        // Case 1: (a,b) is on the +ve slope
        int limit = (a - b) / (2 * b);
        double r1 = 0;
        for (int k = limit; k > 0; k--) {
            double x = (a - b) * 1.0 / (2 * k);
            if (b <= x && a >= 2.0 * k * x && a <= (2.0 * k * x + x)) {
                r1 = x;
                break;
            }
        }
        // Case 2: (a,b) is on the -ve slope
        limit = (a + b) / (2 * b) - 1;
        double r2 = 0;
        for (int k = limit; k >= 0; k--) {
            double x = (a + b) * 1.0 / (2 * k + 2);
            if (b <= x && a >= (2.0 * k * x + x) && a <= (2.0 * k * x + 2 * x)) {
                r2 = x;
                break;
            }
        }
        if (r1 == 0 && r2 != 0) res = r2;
        else if (r2 == 0 && r1 != 0) res = r1;
        else if (r1 != 0 && r2 != 0) res = min(r1, r2);
        else res = -1;
    }
    printf("%0.9f\n", res);
}
