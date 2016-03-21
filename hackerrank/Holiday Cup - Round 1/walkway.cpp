#include <iostream>
#include <stdio.h>
#include <limits.h>
#include <vector>
#include <unordered_set>
#define MAX 1000

using namespace std;

class Stone {
public:
	int a, b, h;
};

inline int calc_price(Stone *st) {
	return (st->a + st->b) * st->h;
}

int find_min(unordered_set<int> &queue, int *dist) {
	int res = -1, min = INT_MAX;
	for (auto iter = queue.begin(); iter != queue.end(); iter++) {
		if (dist[*iter] < min) {
			res = *iter;
			min = dist[*iter];
		}
	}
	return res;
}

int main() {
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	while (n) {
		vector<Stone*> sides[MAX + 1];
		unordered_set<int> queue;

		for (int i = 0; i < n; i++) {
			Stone *s1 = new Stone();
			cin >> s1->a >> s1->b >> s1->h;
			Stone *s2 = new Stone();
			s2->a = s1->b;
			s2->b = s1->a;
			s2->h = s1->h;
			sides[s1->a].push_back(s1);
			sides[s2->a].push_back(s2);
			queue.insert(s1->a);
			queue.insert(s2->a);
		}

		int src, target;
		cin >> src >> target;

		int dist[MAX + 1];
		for (int i = 0; i < MAX + 1; i++)
			dist[i] = INT_MAX;
		dist[src] = 0;

		// Dijkstra
		while (!queue.empty()) {
			int min = find_min(queue, dist);
			if (min == target) break;
			queue.erase(min);
			int alt;
			for (auto s : sides[min]) {
				alt = dist[min] + calc_price(s);
				if (dist[s->b] > alt) 
					dist[s->b] = alt;
			}
		}

		double res = (double)dist[target] / 100;
		printf("%.2f\n", res);
		cin >> n;
	}
}














