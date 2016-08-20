#include <iostream>
#include <string>
#include <vector>
#include <unordered_map>
#include <unordered_set>

using namespace std;

int p, n;
int seen[2][605];
char grid[2][605];

bool verify(int r, int c) {
	if (c < 0 || c == n || seen[r][c]) 
		return false;
	if (seen[1 - r][c]) {
		if ((c - 1 >= 0 && !seen[r][c - 1]) && (c + 1 < n && !seen[r][c + 1]))
			return false;
	}
	return true;
}

void explore(int r, int c, int cnt, string& s, unordered_set<string>& res) {
	cnt++;
	seen[r][c] = 1;
	s.push_back(grid[r][c]);

	if (cnt == 2 * n) {
		res.insert(s);
	}

	if (verify(r, c - 1)) {
		explore(r, c - 1, cnt, s, res);
	}

	if (verify(r, c + 1)) {
		explore(r, c + 1, cnt, s, res);
	}

	if (verify(1 - r, c)) {
		explore(1 - r, c, cnt, s, res);
	}

	cnt--;
	seen[r][c] = 0;
	s.pop_back();
}

int solve() {
	unordered_set<string> res;
	for (int i = 0; i < 2; i++)
		for (int j = 0; j < n; j++) seen[i][j] = 0;

	for (int r = 0; r < 2; r++) {
		for (int c = 0; c < n; c++) {
			string s;
			explore(r, c, 0, s, res);
		}
	}

	return res.size();
}

int main() {
	ios_base::sync_with_stdio(0);
	cin >> p;
	for (int test = 1; test <= p; test++) {
		cin >> n;
		for (int j = 0; j < 2; j++) cin >> grid[j];
		cout << solve() << endl;
	}
}
