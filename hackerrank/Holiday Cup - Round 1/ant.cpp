#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <climits>

using namespace std;

typedef pair<int, int> node;

struct node_hash {
	public:
		template <typename T, typename U>
		size_t operator()(const pair<T, U> &x) const {
			return hash<T>()(x.first) ^ hash<U>()(x.second);
		}
};

pair<int, int> find_min(unordered_set<node, node_hash> &queue, 
	unordered_map<node, int, node_hash> &dist);

int main() {
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		unordered_map<node, unordered_set<node, node_hash>, node_hash> adj;
		int x = 0, y = 0, s;
		cin >> s;
		for (int j = 0; j < s; j++) {
			char dir;
			cin >> dir;
			int dx, dy;
			if (dir == 'N') {
				dx = 0, dy = 1;
			} else if (dir == 'S') {
				dx = 0, dy = -1;
			} else if (dir == 'W') {
				dx = -1, dy = 0;
			} else {
				dx = 1, dy = 0;
			}
			node cur = make_pair(x, y);
			node next = make_pair(x + dx, y + dy);
			adj[cur].insert(next);
			adj[next].insert(cur);
			x += dx;
			y += dy;
		}
		node src = make_pair(0, 0);
		node dest = make_pair(x, y);

		// dijkstra
		unordered_set<node, node_hash> queue;
		unordered_map<node, int, node_hash> dist;
		for (auto iter = adj.begin(); iter != adj.end(); iter++) {
			dist[iter->first] = INT_MAX;
			queue.insert(iter->first);
		}

		dist[src] = 0;
		while (!queue.empty()) {
			node min = find_min(queue, dist);
			queue.erase(min);
			if (min == dest) {
				break;
			}


			int alt = dist[min] + 1;
			for (auto iter = adj[min].begin(); iter != adj[min].end(); iter++) {
				if (alt < dist[*iter]) {
					dist[*iter] = alt;
				}
			}
		}

		cout << dist[dest] << endl;
	}	
}

node find_min(unordered_set<node, node_hash> &queue, 
	unordered_map<node, int, node_hash> &dist) {
	int min = INT_MAX;
	node res;
	for (auto iter = queue.begin(); iter != queue.end(); iter++) {
		if (min > dist[*iter]) {
			min = dist[*iter];
			res = *iter;
		}
	}
	return res;
}