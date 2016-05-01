#include <stdio.h>
#include <iostream>
#include <vector>
#include <unordered_set>
// #define TEST

using namespace std;
const int MAX_N = 1005;
int n, parent[MAX_N];
bool seen[MAX_N];

int getHeight(int node, const vector<vector<int> > & child)
{
	seen[node] = true;
	int result = 0;
	for (int c : child[node]) {
		if (parent[node] != c)
			result = max(result, 1 + getHeight(c, child));
	}
	return result;
}

void solve(int test)
{
	cin >> n;
	vector<vector<int> > child;
	child.resize(n + 1);
	for (int i = 1; i <= n; i++) {
		cin >> parent[i];
		seen[i] = false;
		child[parent[i]].push_back(i);
	}

	int result = 0;
	for (int start = 1; start <= n; start++) {
		if (seen[start]) continue;
		int frontier = start;
		while (!seen[frontier]) {
			seen[frontier] = true;
			frontier = parent[frontier];
		}

		// A knot is found
		int cnt;
		if (parent[parent[frontier]] == frontier) {
			cnt = 2 + getHeight(frontier, child) + getHeight(parent[frontier], child);
		} else {
			cnt = 1;
			int temp = frontier;
			int last = frontier;
			frontier = parent[frontier];
			while (frontier != temp) {
				cnt++;
				for (int c : child[frontier]) 
					if (c != last)
						getHeight(c, child);
				last = frontier;
				frontier = parent[frontier];
			}
			for (int c : child[temp])
				if (c != last)
					getHeight(c, child);
		}
		result = max(result, cnt);
	}

	cout << "Case #" << test << ": " << result << endl;
}

int main()
{
	#ifndef TEST
	freopen("C-small-practice.in", "r", stdin);
	freopen("C-small-practice.out", "w", stdout);
	#endif
	ios_base::sync_with_stdio(0);

	int test;
	cin >> test;
	for (int tt = 1; tt <= test; tt++)
	{
		solve(tt);
	}
}
