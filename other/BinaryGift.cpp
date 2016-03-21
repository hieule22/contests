//
//  BinaryGift.cpp
//  
//
//  Created by Hieu Le on 9/23/15.
//
//

#include <iostream>
#include <string>

using namespace std;

int solve(string a, string b) {
    if (a.size() != b.size()) return -1;
    int one = 0;
    int zero = 0;
    for (int i = 0; i < a.size(); i++) {
        if (a[i] != b[i]) {
            if (a[i] == '1') one++;
            else zero++;
        }
    }
    if (one != zero) return -1;
    else return one;
}

int main() {
    string a,b;
    cin >> a;
    cin >> b;
    cout << solve(a,b) << endl;
}