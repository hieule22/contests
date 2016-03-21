//
//  suffix_array_demo.cpp
//  
//
//  Created by Hieu Le on 9/14/15.
//
//

#include <stdio.h>
#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

struct suffix {
    int index;
    char* suff;
};

int cmp(struct suffix a, struct suffix b) {
    return strcmp(a.suff, b.suff);
}

int *buildSuffixArray(char* text, int n)
{
    struct suffix suffixes[n];
    
    for (int i = 0; i < n; i++) {
        suffixes[i].index = i;
        suffixes[i].suff = (text + i);
    }
    
    sort(suffixes, suffixes + n, cmp);
    
    int* suffixArr = new int[n];
    for (int i = 0; i < n; i++) {
        suffixArr[i] = suffixes[i].index;
    }
    
    return suffixArr;
}

void printArr(int arr[], int n) {
    for (int i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}

int main() {
    char txt[] = "banana";
    int n = strlen(txt);
    int *suffixArr = buildSuffixArray(txt, n);
    printArr(suffixArr, n);
}