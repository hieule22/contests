//
//  ChessGame.cpp
//  
//
//  Created by Hieu Le on 9/19/15.
//
//

#include <stdio.h>
#include <algorithm>
#define SIZE 50

using namespace std;

int dp[SIZE][SIZE][SIZE][SIZE];

int main() {
    for (int x1 = 0; x1 < SIZE; x1++) {
        for (int y1 = 0; y1 < SIZE; y1++) {
            for (int x2 = 0; x2 < SIZE; x2++) {
                for (int y2 = 0; y2 < SIZE; y2++) {
                    int ans = 0;
                    // Backtrack the king
                    if (x1 >= 2 && y1 >= 1 && !dp[x1 - 2][y1 - 1][x2][y2])
                        ans = 1;
                    if (!ans && x1 >= 1 && y1 >= 2 && !dp[x1 - 1][y1 - 2][x2][y2])
                        ans = 1;
                    // Back track the queen
                    for (int k = 1; k <= x2 && !ans; k++) {
                        if (!dp[x1][y1][x2 - k][y2]) ans = 1;
                    }
                    for (int k = 1; k <= y2 && !ans; k++) {
                        if (!dp[x1][y1][x2][y2 - k]) ans = 1;
                    }
                    for (int k = 1; k <= min(x2,y2) && !ans; k++) {
                        if (!dp[x1][y1][x2 - k][y2 - k]) ans = 1;
                    }
                    // Update the result for this position
                    dp[x1][y1][x2][y2] = ans;
                }
            }
        }
    }
    
    int t;
    scanf("%d", &t);
    while (t--) {
        int x1, y1, x2, y2;
        scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
        if (dp[x1][y1][x2][y2]) printf("WIN\n");
        else printf("LOSE\n");
    }
}

