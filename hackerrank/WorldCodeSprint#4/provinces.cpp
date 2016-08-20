#include <cstdio>
#include <iostream>
#include <cstring>
#include <string>
#include <unordered_set>

using namespace std;

int n;
int seen[2][601];
char grid[2][601];
unordered_set<string> cache;

void explore(int r, int c, string& s, int dc) {
	if (c < 0 || c == n) {
		cache.insert(s);
		cout << s << endl;
		return;
	}

	// Try loop
	string temp = s;
	int cc = c;
	for (; cc >= 0 && cc < n; cc += dc) temp.push_back(grid[r][cc]);
	cc = (cc == n) ? (n - 1) : 0;
	for (; !seen[1 - r][cc]; cc -= dc) temp.push_back(grid[1 - r][cc]);
	cout << s << " " << dc << " " << temp << endl;
	cache.insert(temp);

	// Try zigzag
	seen[r][c] = 1;
	s.push_back(grid[r][c]);
	if (!seen[1 - r][c]) explore(1 - r, c, s, dc);
	else explore(r, c + dc, s, dc);
}

int solve() {
	cache.clear();
	for (int r = 0; r < 2; r++) {
		for (int c = 0; c < n; c++) {
			for (int i = 0; i < 2; i++)
				for (int j = 0; j < n; j++) seen[i][j] = 0;
			// Loop left
			string s0;
			for (int cc = c; cc >= 0; cc--) {
				s0.push_back(grid[r][cc]);
				seen[r][cc] = 1;
			}
			for (int cc = 0; cc <= c; cc++) {
				s0.push_back(grid[1 - r][cc]);		
				seen[1 - r][cc] = 1;
			} 
			explore(1 - r, c + 1, s0, 1);

			// Loop right
			string s1;
			for (int cc = c; cc < n; cc++) {
				s1.push_back(grid[r][cc]);
				seen[r][cc] = 1;
			}
			for (int cc = n - 1; cc >= c; cc--) {
				s1.push_back(grid[1 - r][cc]);
				seen[1 - r][cc] = 1;
			}
			explore(1 - r, c - 1, s1, -1);
		}
	}
	return cache.size();
}

int main() {
	int p;
	scanf("%d", &p);
	for (int i = 0; i < p; i++) {
		scanf("%d %s %s", &n, grid[0], grid[1]);
		printf("%d\n", solve());
	}
}