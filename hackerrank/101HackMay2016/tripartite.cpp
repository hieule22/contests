#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_set>
using namespace std;
const int MAX_N = 100000;
// const int THRESHOLD = sqrt(MAX_N);

int n, m;
unordered_set<int> graph[3][MAX_N];

bool contain(const unordered_set<int> & s, int a)
{
    return s.find(a) != s.end();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin >> n;
    
    for (int i = 0; i < 3; i++) {
        cin >> m;
        int u, v;
        for (int j = 0; j < m; j++) {
            cin >> u >> v;
            graph[i][u - 1].insert(v - 1);
            graph[i][v - 1].insert(u - 1);
        }
    }
    
    int res = 0;
    const int THRESHOLD = sqrt(n);
    
    for (int i = 0; i < n; i++) {
        int total = graph[0][i].size() + graph[1][i].size();
        if (total <= THRESHOLD) {
            for (int u : graph[0][i])
                for (int v : graph[1][i])
                    if (contain(graph[2][u], v))
                        res++;
        } else {
            for (int u = 0; u < n; u++)
                for (int v : graph[2][u])
                    if (contain(graph[0][i], u) && contain(graph[1][i], v))
                        res++;
        }
    }

    cout << res << endl;
}

