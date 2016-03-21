//
//  EnigmaCode.cpp
//  
//
//  Created by Hieu Le on 9/25/15.
//
//

#include <stdio.h>
#include <iostream>
#include <string>
#include <assert.h>
#include <math>
#include <vector>

using namespace std;

typedef long long int_64;

struct Segment {
    
    int l1, r1, l2, r2;
    bool increase;
    
    Segment(int a, int b, int c, int d) {
        assert (a <= b);
        assert (abs(a - b) == abs(c - d));
        l1 = a;
        r1 = b;
        l2 = c;
        r2 = d;
        include = (r2 >= l2);
    }
    
    int size() {
        return r1 - l1;
    }
};

void solve() {
    int n, m;
    int_64 k;
    cin >> n >> m >> k;
    string s;
    cin >> s;
    vector<Segment> frontier;
    frontier.push_back(Segment(0, n - 1, 0));
    
    for (int i = 0; i < m; i++) {
        int x, y;
        cin >> x >> y;
        x--, y--;
        vector<Segment> next;
        for (int i = 0; i < frontier.size(); i++) {
            Segment cur = frontier[i];
            if (cur.r1 < x || cur.l1 > y) next.push_back(cur);
            if (cur.r1 > y)
        }
    }
}

int main() {
    int t;
    cin >> t;
    while (t--) {
        solve();
    }
}
