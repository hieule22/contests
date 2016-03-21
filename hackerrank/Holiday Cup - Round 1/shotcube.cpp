#include <vector>
#include <iostream>
#define N 7
#define CUBE 'X'
#define EMPTY '.'
#define NORTH 1
#define SOUTH 2
#define EAST 3
#define WEST 4

using namespace std;

bool isOk(char **);
bool validate(char **, int, int, int);
char **push(char **, int, int, int);

int main() {
	ios_base::sync_with_stdio(false);
	int T;
	cin >> T;
	while (T--) {
		char **grid = new char*[N];
		for (int i = 0; i < N; i++)
			grid[i] = new char[N];
		char line[N + 1];
		for (int i = 0; i < N; i++) {
			cin >> line;
			for (int j = 0; j < N; j++)
				grid[i][j] = line[j];
		}

		vector<char**> frontier;
		frontier.push_back(grid);
		int cnt = 0;
		bool found = false;

		while (!frontier.empty()) {
			vector<char**> next;
			for (auto iter = frontier.begin(); iter != frontier.end(); iter++) {
				char **cur = *(iter);

				if (isOk(cur)) {
					found = true;
					goto print_result;
				}

				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						for (int dir = NORTH; dir <= WEST; dir++) {
							if (validate(cur, r, c, dir)) {
 								next.push_back(push(cur, r, c, dir));
							}
						}
					}
				}
			}

			for (auto iter = frontier.begin(); iter != frontier.end(); iter++)
				delete *iter;
			frontier = next;
			cnt++;
		}

		print_result:
		cout << (found ? cnt : -1) << endl;
	}
}

bool isOk(char **grid) {
	int r, c;
	for (r = 0; r < N; r++) {
		for (c = 0; c < N; c++) {
			if (grid[r][c] == CUBE)
				goto end_of_scan_loop;
		}
	}

	end_of_scan_loop:
	if (r + 2 >= N || c + 2 >= N) return false;
	for (int i = r; i <= r + 2; i++) {
		for (int j = c; j <= c + 2; j++) {
			if (grid[i][j] != CUBE) return false;
		}
	}

	return true;
}

bool validate(char **grid, int r, int c, int dir) {
	if (r - 1 >= 0 && r + 1 < N && c - 1 >= 0 && c + 1 < N)
		return false;
	if (grid[r][c] == EMPTY) return false;

	int dx, dy;
	switch (dir) {
		case NORTH:
			dx = 0, dy = 1;
			break;
		case SOUTH:
			dx = 0, dy = -1;
			break;
		case WEST:
			dx = -1, dy = 0;
			break;
		case EAST:
			dx = 1, dy = 0;
			break;
		default:
			return false;
	}

	bool hasEmpty = false;
	int row, col;
	for (row = r, col = c; row < N && row >= 0 
		&& col < N && col >= 0; row += dy, col += dx) {
		if (grid[row][col] == EMPTY) {
			hasEmpty = true;
			break;
		}
	}

	if (!hasEmpty) return false;
	bool hasCube = false;
	for (; row < N && row >= 0 && col < N && col >= 0; row += dy, col += dx) {
		if (grid[row][col] == CUBE) {
			hasCube = true;
			break;
		}
	}

	return hasCube;
}

char **push(char **grid, int r, int c, int dir) {
	int dx, dy;
	switch (dir) {
		case NORTH: 
			dx = 0, dy = 1;
			break;
		case SOUTH: 
			dx = 0, dy = -1;
			break;
		case EAST:
			dx = 1, dy = 0;
			break;
		case WEST:
			dx = -1, dy = 0;
			break;
	}

	char** res = new char*[N];
	for (int i = 0; i < N; i++) {
		res[i] = new char[N]; 
		for (int j = 0; j < N; j++) res[i][j] = grid[i][j];
	}
	int row, col;
	int cnt = 0;
	for (row = r, col = c; row < N && row >= 0 && col < N && col >= 0; row += dy, col += dx) {
		if (grid[row][col] == EMPTY) {
			break;
		} else {
			cnt++;
		}
	}

	int steps = 1;
	while (grid[row + steps * dy][col + steps * dx] == EMPTY) {
		steps++;
	}

	for (int i = 0; i < cnt; i++) {
		res[r + i * dy + steps * dy][c + i * dx + steps * dx] = CUBE;
	}

	for (int i = 0; i < steps; i++)
		res[r + i * dy][c + i * dx] = EMPTY;
	return res;
}






