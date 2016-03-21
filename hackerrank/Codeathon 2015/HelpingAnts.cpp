//
//  HelpingAnts.cpp
//  
//
//  Created by Hieu Le on 9/26/15.
//
//

#include <stdio.h>
#include <unordered_set>
#define MOD 1000000007

using namespace std;


typedef long long int_64;

int main() {
    unordered_set<int_64> modulo;
    int_64 val = 6;
    int cnt = 0;
    while (true) {
        if (modulo.find(val) != modulo.end()) break;
        modulo.insert(val);
        val = val * 3 + 1;
        val = val % MOD;
        cnt++;
    }
    printf("%d\n", cnt);
}