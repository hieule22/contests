#include <cmath>
#include <vector>
#include <iostream>
#include <unordered_map>
#define N 10005

using namespace std;

inline int find_gcd(int a, int b);


int n, m, cnt;
bool visited[N];
int divisor[N];
vector<vector<pair<int, int> > > adj;

void dfs(int node, unordered_map<int, int> &mod) {
	if (mod.find(1) != mod.end()) mod[1]++;
	visited[node] = true;
	for (auto road : adj[node]) {
		if (!visited[road.first]) {
			unordered_map<int, int> child_mod;
			for (auto p : mod) {
				int remain = p.first / find_gcd(road.second, p.first);
				child_mod[remain] = 0;
			}
			child_mod[m] = 0;
			dfs(road.first, child_mod);
			for (auto q : child_mod) {
				if (q.second) {
					int product = q.second * road.second;
					int gcd = find_gcd(m, product);
					mod[gcd] += q.second;
				}
			}
		}
	}
	cnt += mod[m];
}

int main() 
{
	ios_base::sync_with_stdio(false);

	cin >> n >> m;
	int a, b, c;
	adj.reserve(n + 1);
	for (int i = 0; i < n - 1; i++) {
		cin >> a >> b >> c;
		cout << "Done" << endl;
		adj[a].push_back(make_pair(b, c));
		adj[b].push_back(make_pair(a, c));
	}

	for (int i = 0; i < n + 1; i++)
		visited[i] = false;

	int root = 1;
	// dfs
	cnt = 0; 
	unordered_map<int, int> mod;
	mod[m] = 0;
	dfs(1, mod);
	cout << cnt << endl;
}

inline int find_gcd(int a, int b) {
	int temp;
	while (b) {
		temp = b;
		b = a % b;
		a = temp;
	}
	return a;
}