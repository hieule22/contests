//
//  FlipFlopString.cpp
//  
//
//  Created by Hieu Le on 9/19/15.
//
//

#include <stdio.h>
#include <iostream>
#include <string>

using namespace std;

int main() {
    int t;
    cin >> t;
    while (t--) {
        string s;
        cin >> s;
        // First char is 'X'
        int c1 = 0;
        char cur = 'X';
        for (int i = 0; i < s.size(); i++) {
            if (s[i] == cur) {
                if (cur == 'X') cur = 'Y';
                else cur = 'X';
            } else c1 ++;
        }
        // First char is 'Y'
        int c2 = 0;
        cur = 'Y';
        for (int i = 0; i < s.size(); i++) {
            if (s[i] == cur) {
                if (cur == 'X') cur = 'Y';
                else cur = 'X';
            } else c2 ++;
        }
        
        cout << ((c1 < c2) ? c1 : c2) << endl;
    }
    
}
