#include <stdio.h>
#include <algorithm>
#include <unordered_set>

using namespace std;

int l, r, q;
char **grid;

typedef pair<int, int> Point;

class PointHash
{
public:
	size_t operator() (Point point) const {
		return point.first ^ point.second;
	}
};

int find_max(char gender)
{
	unordered_set<Point, PointHash> corners;
	for (int i = 0; i < l; i++) {
		for (int j = 0; j < r; j++) {
			if (grid[i][j] == gender)
				corners.insert(make_pair(i, j));
		}
	}

	int res = 0;
	while (!corners.empty()) {
		res++;
		unordered_set<Point, PointHash> frontier;

		for (auto & cur : corners) {
			Point next = make_pair(cur.first + 1, cur.second + 1);
			if (corners.find(next) != corners.end() &&
				grid[cur.first][cur.second + res] == gender &&
				grid[cur.first + res][cur.second] == gender) 
				frontier.insert(cur);
		}

		corners = frontier;
	}

	return res;
}

int main()
{

	scanf("%d%d%d", &l, &r, &q);

	grid = new char*[l];
	for (int i = 0; i < l; i++) {
		grid[i] = new char[r + 1];
		scanf("%s", grid[i]);
	}

	int max_m = find_max('M');
	int max_f = find_max('F');

	int k;
	char gender;
	for (int i = 0; i < q; i++) {
		scanf("%d%s", &k, &gender);
		// gender = getchar();
		if (gender == 'M') 
			printf((k <= max_m) ? "yes\n" : "no\n");
			// cout << ((k <= max_m) ? "yes" : "no") << endl;
		else
			printf((k <= max_f) ? "yes\n" : "no\n");
			// cout << ((k <= max_f) ? "yes" : "no") << endl;
	}
}