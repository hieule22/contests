//
//  bishop_game.cpp
//  
//
//  Created by Hieu Le on 9/14/15.
//
//

#include <stdio.h>
#define MAX 10

using namespace std;

int N, M, res = 0;
char cell[MAX][MAX];
int board[MAX][MAX];

bool check(int row, int col) {\
    
    // Check all the diagonals coming from the top left
    for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
        if (cell[r][c] == '*') break;
        if (board[r][c]) return false;
    }
    
    // Check all the diagonals coming from the top right
    for (int r = row, c = col; r >= 0 && c < M; r--, c++) {
        if (cell[r][c] == '*') break;
        if (board[r][c]) return false;
    }
    
    return true;
}

void solve(int row) {
    if (row >= N) {
        res ++;
        return;
    }
    
    // Place a bishop in every column of the current row
    for (int c = 0; c < M; c++) {
        if (cell[row][c] == '*') continue;
        if (check(row, c)) {
            board[row][c] = 1;
            solve(row + 1);
            // Remove bishop to backtrack
            board[row][c] = 0;
        }
    }
}

int main() {
    scanf("%d %d", &N, &M);
    for (int i = 0; i < N; i++)
        scanf("%s", cell[i]);
    solve(0);
    printf("%d\n", res);
}