#include <iostream>
#include <deque>
using namespace std;

const int MOD = 100000;
typedef unsigned long long uint64;

int main() {
	uint64 src, target;
	int n;
	cin >> src >> target >> n;
	uint64 keys[n];
	for (int i = 0; i < n; i++)
		cin >> keys[i];

	uint64 dist[MOD + 1];
	for (int i = 0; i < MOD + 1; i++)
		dist[i] = -1;

	deque<uint64> frontier;
	frontier.push_back(src % MOD);
	dist[src % MOD] = 0;

	uint64 cur, product;
	while (!frontier.empty()) {
		cur = frontier.front();
		if (cur == target)
			break;
		frontier.pop_front();
		for (int i = 0; i < n; i++) {
			product = (cur * keys[i]) % MOD;
			if (dist[product] == -1) {
				dist[product] = dist[cur] + 1;
				frontier.push_back(product);
			}
		}
	}

	cout << dist[target] << endl;
}