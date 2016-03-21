//
//  QuantumLand.cpp
//  
//
//  Created by Hieu Le on 9/19/15.
//
//

#include <stdio.h>
#include <math.h>

using namespace std;

int main() {
    int n;
    scanf("%d", &n);
    int next[n + 1]; // The adjacent node
    int prob[n + 1]; // The edge probability
    for (int i = 1; i <= n; i++) {
        scanf("%d %d", &next[i], &prob[i]);
    }
    
    double res = 0;
    int visited[n + 1];
    int cycle[n + 1];
    // Set all nodes to unvisited
    for (int i = 1; i <= n; i++) visited[i] = 0;
    // Traverse the graph
    for (int i = 1; i <= n; i++) {
        if (visited[i]) continue;
        int cur = i;
        while (!visited[cur]) {
            visited[cur] = i;
            cur = next[cur];
        }
        if (visited[cur] != i) continue;
        double p = 1;
        int root = cur;
        do {
            p *= prob[cur] / 100.0;
            cur = next[cur];
        } while (cur != root);
        
        res += p;
    }
    
    res = roundf(res * 100) / 100;
    printf("%.2f\n", res);
}
