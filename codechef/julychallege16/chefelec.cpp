#include <iostream>
#include <climits>
#include <vector>
using namespace std;

const int MAX = 1e5;
char has[MAX + 1];
int dist[MAX];
int n;

void Solve() {
	cin >> n;
	cin >> has;
	for (int i = 0; i < n; i++) {
		cin >> dist[i];
	}

	vector<pair<int, int> > sect;
	int low = -1, high = -1;
	for (int i = 0; i < n; ++i) {
		if (has[i] == '1' && high > -1) {
			sect.push_back(make_pair(low, high));
			low = high = -1;
		} else if (has[i] == '0') {
			if (low == -1) {
				high = low = i;
			} else {
				high = i;
			}
		}
	}
	if (high == n - 1)
		sect.push_back(make_pair(low, high));

	int res = 0;
	for (auto& s : sect) {
		if (s.first == 0) {
			res += dist[s.second + 1] - dist[s.first];
		} else if (s.second == n - 1) {
			res += dist[s.second] - dist[s.first - 1];
		} else {
			int gap = 0;
			for (int i = s.first; i <= s.second + 1; i++)
				gap = max(gap, dist[i] - dist[i - 1]);
			res += (dist[s.second + 1] - dist[s.first - 1] - gap);
		}
	}

	cout << res << endl;
}

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; ++i)
		Solve();
}