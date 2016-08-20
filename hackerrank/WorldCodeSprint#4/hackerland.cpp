#include <iostream>
#include <cstring>
#include <string>
#include <vector>
#include <algorithm>
#include <unordered_map>
#include <cstdint>

using namespace std;

struct Edge {
	int a, b, c;
};

int n, m;
int root[100001], cnt[100001], parent[100001];
int64_t power[300001];
unordered_map<int, vector<int> > graph;
vector<Edge> residue;

bool operator < (const Edge& lhs, const Edge& rhs) {
	return lhs.c < rhs.c;
}

int GetRoot(int a) {
	if (root[a] == 0)
		return a;
	return root[a] = GetRoot(root[a]);
}

int CountChildNodes(int current) {
	cnt[current] = 0;
	for (auto neighbor : graph[current]) {
		if (neighbor == parent[current]) continue;
		parent[neighbor] = current;
		cnt[current] += CountChildNodes(neighbor);
	}
	return ++cnt[current];
}

string Trim(const string& a) {
	int i = 0;
	while (i < a.length() && a[i] == '0')
		i++;
	string res;
	if (i == a.length())
		res = "0";
	else
		res.append(a, i, string::npos);
	return res;
}

int main() {
	ios_base::sync_with_stdio(0);
	cin >> n >> m;
	vector<Edge> edges;
	for (int i = 0; i < m; i++) {
		Edge edge;
		cin >> edge.a >> edge.b >> edge.c;
		edges.push_back(edge);
	}

	sort(edges.begin(), edges.end());
	
	// Construct tree by eliminating redundant edges.
	memset(root, 0, sizeof root);
	for (auto& edge : edges) {
		int ra = GetRoot(edge.a);
		int rb = GetRoot(edge.b);
		if (ra != rb) {
			root[ra] = rb;
			graph[edge.a].push_back(edge.b);
			graph[edge.b].push_back(edge.a);
			residue.push_back(edge);
		}
	}

	// Count the number of nodes in the subtree rooted at every node.
	int src = 1;
	parent[src] = 0;
	CountChildNodes(src);

	// Calculate the number times each residual edge is traversed.
	for (auto& edge : residue) {
		int low = (parent[edge.a] == edge.b) ? edge.a : edge.b;
		power[edge.c] = cnt[src] - cnt[low];
		power[edge.c] *= cnt[low];
	}

	int length = sizeof(power) / sizeof(power[0]);
	for (int i = 0; i < length - 1; i++) {
		int temp = (power[i] >> 1);
		power[i] = (power[i] & 1);
		power[i + 1] += temp;
	}

	string ans;
	for (int i = length - 1; i >= 0; i--) {
		char digit = (power[i] == 1) ? '1' : '0';
		ans.push_back(digit);
	}

	ans = Trim(ans);
	cout << ans << endl;
}