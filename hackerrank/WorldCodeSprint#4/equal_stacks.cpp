#include <cstdio>
#include <unordered_set>
#include <vector>

using namespace std;
int n[3];
int h[3][100000];

int main() {
	for (int i = 0; i < 3; i++)
		scanf("%d", n + i);
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < n[i]; j++)
			scanf("%d", &h[i][j]);
	}

	vector<int> first;
	first.push_back(0);
	int suffix = 0;
	for (int i = n[0] - 1; i >= 0; i--) {
		suffix += h[0][i];
		first.push_back(suffix);
	}

	vector<unordered_set<int> > stacks;
	for (int i = 1; i < 3; i++) {
		stacks.push_back(unordered_set<int>());
		stacks[i - 1].insert(0);
		suffix = 0;
		for (int j = n[i] - 1; j >= 0; j--) {
			suffix += h[i][j];
			stacks[i - 1].insert(suffix);
		}
	}

	int res = 0;
	for (auto iter = first.rbegin(); iter != first.rend(); ++iter) {
		int value = *iter;
		if (stacks[0].find(value) != stacks[0].end() &&
			stacks[1].find(value) != stacks[1].end()) {
			res = value;
			break;
		}
	}
	printf("%d\n", res);
}