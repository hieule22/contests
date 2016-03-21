//
//  Query.cpp
//  
//
//  Created by Hieu Le on 9/26/15.
//
//

#include <stdio.h>
#define MAX 100005

using namespace std;

struct Query {
    int l, k;
};

int a[MAX];
Query query[MAX];
int res[MAX];
int n, q;

int main() {
    scanf("%d %d", &n, &q);
    for (int i = 0; i < n; i++) {
        scanf("%d", a + i);
    }
    for (int i = 0; i < q; i++) {
        int l, k;
        scanf("%d %d", &l, &k);
        query[i].l = l;
        query[i].k = k;
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < q; j++) {
            if (query[j].l <= a[i]) {
                query[j].k--;
                if (query[j].k == 0) res[j] = a[i];
            }
        }
    }
    for (int i = 0; i < q; i++) {
        printf("%d\n", res[i]);
    }
}
