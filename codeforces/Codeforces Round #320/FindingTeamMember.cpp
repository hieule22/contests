//
//  FindingTeamMember.cpp
//  
//
//  Created by Hieu Le on 9/16/15.
//
//

#include <stdio.h>
#include <set>


using namespace std;

struct Team {
    int strength, i, j;
    
    Team(int& a, int& b, int& c): strength(a), i(b), j(c) {};
};

struct compare {
    bool operator()(const Team& a, const Team& b) {
        if (a.strength < b.strength) return true;
        return false;
    }
};

int main() {
    int n;
    scanf("%d", &n);
    set<Team, compare> teams;
    for (int i = 2; i <= 2 * n; i++) {
        for (int j = 1; j <= i - 1; j++) {
            int strength;
            scanf("%d", &strength);
            teams.insert(Team(strength, i, j));
        }
    }
    int res[2 * n + 1];
    for (int i = 1; i <= 2 * n; i++) res[i] = 0;
    for (set<Team, compare>::reverse_iterator rit = teams.rbegin(); rit != teams.rend(); ++rit) {
        if (!res[rit->i] && !res[rit->j]) {
            res[rit->i] = rit->j;
            res[rit->j] = rit->i;
        }
    }
    for (int i = 1; i <= 2 * n; i++)
        printf("%d ", res[i]);
}