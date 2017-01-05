#include <cstdio>
#include <climits>
#include <utility>
#include <deque>

using namespace std;

#define MAX_N 100
#define N_DIRECTIONS 4
#define START 'S'
#define GOAL 'G'
#define EMPTY '.'
#define WALL '#'

const int DR[] = {0, -1, 0, 1};
const int DC[] = {-1, 0, 1, 0};
const char TURRETS[] = {'<', '^', '>', 'v'};

int rows, cols;
char maze[MAX_N][MAX_N + 1];
int steps[MAX_N][MAX_N][N_DIRECTIONS];

// Checks if (r, c) references a valid cell in maze.
bool IsValidCell(int r, int c) {
  return !(r < 0 || c < 0 || r >= rows || c >= cols);
}

// Checks if (r, c) is being attacked in given direction in given timestamp.
bool IsAttacked(int r, int c, int dr, int dc, int timestamp) {
  // Locate nearest turret in given direction.
  while (IsValidCell(r, c) &&
         (maze[r][c] == EMPTY || maze[r][c] == START || maze[r][c] == GOAL)) {
    r += dr;
    c += dc;
  }

  if (!IsValidCell(r, c) || maze[r][c] == WALL)
    return false;

  // Determine direction of turret in given timestamp.
  int index = find(TURRETS, TURRETS + N_DIRECTIONS, maze[r][c])
      - std::begin(TURRETS);
  index = (index + timestamp) % N_DIRECTIONS;
  return DR[index] + dr == 0 && DC[index] + dc == 0;
}

// Checks if cell (r, c) is safe in given timestamp.
bool IsSafe(int r, int c, int timestamp) {
  if (!IsValidCell(r, c))
    return false;
  // Cell (r, c) is a turret.
  if (maze[r][c] != EMPTY && maze[r][c] != START && maze[r][c] != GOAL)
    return false;
  for (int i = 0; i < N_DIRECTIONS; ++i) {
    if (IsAttacked(r, c, DR[i], DC[i], timestamp))
      return false;
  }
  return true;
}

void Solve(int test_number) {
  scanf("%d%d", &rows, &cols);
  pair<int, int> start, goal;
  for (int i = 0; i < rows; ++i) {
    scanf("%s", maze[i]);
    for (int j = 0; j < cols; ++j) {
      if (maze[i][j] == START)
        start = {i, j};
      if (maze[i][j] == GOAL)
        goal = {i, j};
    }
  }

  for (int i = 0; i < rows; ++i)
    for (int j = 0; j < cols; ++j)
      for (int k = 0; k < N_DIRECTIONS; ++k)
        steps[i][j][k] = INT32_MAX;

  steps[start.first][start.second][0] = 0;
  deque<pair<int, int>> frontier;
  frontier.push_back({start.first, start.second});
  int time = 0;
  while (!frontier.empty()) {
    ++time;
    int timestamp = (time % N_DIRECTIONS);
    int cnt = frontier.size();
    for (int i = 0; i < cnt; ++i) {
      pair<int, int> head = frontier.front();
      frontier.pop_front();
      for (int j = 0; j < N_DIRECTIONS; ++j) {
        int r = head.first + DR[j], c = head.second + DC[j];
        if (IsSafe(r, c, timestamp) && steps[r][c][timestamp] == INT32_MAX) {
          steps[r][c][timestamp] = time;
          frontier.push_back({r, c});
        }
      }
    }
  }

  int result = INT32_MAX;
  for (int i = 0; i < N_DIRECTIONS; ++i)
    result = min(result, steps[goal.first][goal.second][i]);

  printf("Case #%d: ", test_number);
  if (result == INT32_MAX)
    printf("impossible\n");
  else
    printf("%d\n", result);
}

int main() {
  int tests;
  scanf("%d", &tests);
  for (int test_number = 1; test_number <= tests; ++test_number)
    Solve(test_number);
  return 0;
}
