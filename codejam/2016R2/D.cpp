#include <iostream>
#include <climits>
#include <cstring>

using namespace std;
int n, ans;
unsigned int can[30];
unsigned int ban[30];

bool isSubset(unsigned int superset, unsigned int subset)
{
	for (int i = 0; i < n; i++) {
		if (!(superset & (1 << i)) && (subset & (1 << i)))
			return false;
	}
	return true;
}

bool test(int k, int * taken, int * order)
{
	if (k == n) {
		for (int i = 0; i < n; i++)
			if (!taken[i])
				return false;
		return true;
	}

	bool hasJob = false;
	for (int i = 0; i < n; i++) {
		if (!taken[i] && (ban[order[k]] & (1 << i))) {
			hasJob = true;
			taken[i] = 1;
			if (!test(k + 1, taken, order))
				return false;
			taken[i] = 0;
		}
	}

	if (!hasJob && !test(k + 1, taken, order))
		return false;
	return true;
}

bool permute(int *order, int l, int r)
{
	if (l == r) {
		int taken[n];
		memset(taken, 0, sizeof taken);
		if (!test(0, taken, order))
			return false;
		return true;
	}

	for (int i = l; i <= r; i++) {
		swap(order[l], order[i]);
		if (!permute(order, l + 1, r))
			return false;
		swap(order[l], order[i]);
	}

	return true;
}

void recurse(int k)
{
	if (k == n) {
		int order[n];
		for (int i = 0; i < n; i++)
			order[i] = i;
		if (permute(order, 0, n - 1)) {
			int temp = 0;
			for (int i = 0; i < n; i++) {
				temp += __builtin_popcount(ban[i]) - __builtin_popcount(can[i]);
			}
			ans = min(ans, temp);
		}
		return;
	}

	for (unsigned int mask = 0; mask < (1 << n); mask++) {
		if (isSubset(mask, can[k])) {
			ban[k] = mask;
			recurse(k + 1);
		}
	}
}

void solve(int testNumber)
{
	cin >> n;
	char temp[30];
	for (int i = 0; i < n; i++) {
		cin >> temp;
		can[i] = (unsigned int)strtol(temp, NULL, 2);
	}

	ans = INT_MAX;
	recurse(0);
	cout << "Case #" << testNumber << ": " << ans << endl;
}

int main()
{
	ios_base::sync_with_stdio(0);
	int tests;
	cin >> tests;
	for (int tt = 1; tt <= tests; tt++)
		solve(tt);
}