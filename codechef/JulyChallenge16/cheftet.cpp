#include <iostream>
#include <cstdint>
#include <cstring>

using namespace std;

const int MAX = 10000;
int n, a[MAX], b[MAX], taken[MAX];

void Solve() {
	cin >> n;
	uint64_t total = 0;
	for (int i = 0; i < n; i++) {
		cin >> b[i];
		total += b[i];
	}
	for (int i = 0; i < n; i++) {
		cin >> a[i];
		total += a[i];
	}

	if (total % n != 0) {
		cout << -1 << endl;
		return;
	}

	uint64_t cnt = total / n;
	memset(taken, 0, sizeof taken);
	bool ok = true;
	for (int i = 0; i < n; i++) {
		if (i - 1 >= 0 && !taken[i - 1]) {
			a[i] += b[i - 1];
			taken[i - 1] = 1;
		}

		if (!taken[i] && a[i] + b[i] == cnt) {
			taken[i] = 1;
			continue;
		}

		if (i + 1 < n && a[i] + b[i + 1] == cnt) {
			taken[i + 1] = 1;
			continue;
		}

		if (i + 1 < n && !taken[i] && a[i] + b[i] + b[i + 1] == cnt) {
			taken[i] = taken[i + 1] = 1;
			continue;
		}

		if (a[i] != cnt) {
			ok = false;
			break;
		}
	}

	int res = ok ? cnt : -1;
	cout << res << endl;
}

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; i++)
		Solve();
}