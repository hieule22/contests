//
//  Palindrome.cpp
//  
//
//  Created by Hieu Le on 9/23/15.
//
//

#include <stdio.h>
#include <iostream>
#include <string>
#include <unordered_map>
#include <assert.h>

using namespace std;

typedef long long int_64;

int_64 findGcd(int_64 a, int_64 b) {
    while (b) {
        int_64 temp = b;
        assert (b);
        b = a % b;
        a = temp;
    }
    return a;
}

int_64 getMask(int freq[]) {
    long res = 0;
    for (int i = 0; i < 26; i++) {
        if (freq[i]) res += (1 << i);
    }
    return res;
}

int count(int_64 mask, unordered_map<int_64, int> prefix) {
    int res = 0;
    if (prefix.find(mask) != prefix.end()) res += prefix[mask];
    for (int i = 0; i < 26; i++) {
        long complement;
        if (mask & (1 << i)) complement = mask - (1 << i);
        else complement = mask + (1 << i);
        if (prefix.find(complement) != prefix.end()) res += prefix[complement];
    }
    return res;
}

int main() {
    int t;
    cin >> t;
    while (t--) {
        string s;
        cin >> s;
        int freq[26];
        for (int i = 0; i < 26; i++) {
            freq[i] = 0;
        }
        unordered_map<int_64, int> prefix;
        prefix[(int_64)0] = 1;
        
        int_64 cnt = 0;
        string::iterator iter;
        for (iter = s.begin(); iter != s.end(); iter++) {
            int pos = *iter - 'a';
            freq[pos] ^= 1;
            int_64 mask = getMask(freq);
            cnt += count(mask, prefix);
            if (prefix.find(mask) == prefix.end()) prefix[mask] = 1;
            else prefix[mask]++;
        }
        
        int_64 total = (int_64)s.size() * (s.size() + 1) / 2;
        int_64 gcd = findGcd(cnt, total);
        while (gcd > 1) {
            assert (gcd);
            cnt /= gcd;
            total /= gcd;
            gcd = findGcd(cnt, total);
        }
        cout << cnt << "/" << total << endl;
    }
}