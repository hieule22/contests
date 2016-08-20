#include <cstdio>
#include <climits>
#include <unordered_map>
#define min(a, b) ((a < b) ? a : b)

using namespace std;

int a, n;

int main() {
	scanf("%d", &n);
	unordered_map<int, int> last;
	unordered_map<int, int> delta;

	for (int i = 0; i < n; i++) {
		scanf("%d", &a);
		if (last.find(a) == last.end()) {
			delta[a] = INT_MAX;
		} else {
			delta[a] = min(delta[a], i - last[a]);
		}
		last[a] = i;
	}

	int res = INT_MAX;
	for (auto element : delta) {
		res = min(res, element.second);
	}
	if (res == INT_MAX) 
		printf("-1\n");
	else
		printf("%d\n", res);
}	