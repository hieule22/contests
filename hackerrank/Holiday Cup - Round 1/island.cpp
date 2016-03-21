#include <string.h>
#include <iostream>
#include <stdio.h>
#define WATER '.'
#define BRIDGE 'B'
#define MAX 90

char map[MAX][MAX];
int island[MAX][MAX];
int adj[MAX][MAX];
int height, width, cnt;

void dfs(int i, int j, int cnt) {
	if (island[i][j] || map[i][j] == WATER || map[i][j] == BRIDGE)
		return;
	island[i][j] = cnt;
	if (i - 1 >= 0) dfs(i - 1, j, cnt);
	if (i + 1 < height) dfs(i + 1, j, cnt);
	if (j - 1 >= 0) dfs(i, j - 1, cnt);
	if (j + 1 < width) dfs(i, j + 1, cnt);
}

int main() {
	for (height = 0; height < MAX; height++) {
		scanf("%s", map[height]);
		if (strlen(map[height]) == 0)
			break;
	}
	int width = strlen(map[0]);

	for (int i = 0; i < height; i++)
		for (int j = 0; j < width; j++)
			island[i][j] = 0; 

	cnt = 0;
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			if (!island[i][j] && map[i][j] != WATER && map[i][j] != BRIDGE) {
				cnt++;
				dfs(i, j, cnt);
			}
		}
	}

	for (int i = 0; i < cnt + 1; i++)
		for (int j = 0; j < cnt; j++)
			adj[i][j] = 0;

	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			if (map[i][j] == BRIDGE) {
				
			}
		}
	}
}