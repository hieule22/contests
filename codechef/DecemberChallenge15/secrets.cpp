#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <limits.h>
#include <utility>

#define SECRETS 32

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	int n, m;
	cin >> n >> m;

	vector<pair<int, int> > crushEdges;
	vector<int> crushFriends;
	int edges[n + 1][2];
	int friends[n + 1];

	for (int i = 0; i < n + 1; i++)
		friends[i] = -1;

	int first, second, l, r;
	for (int k = 0; k < m; k++) {
		int first, second, l, r;
		cin >> first >> second >> l >> r;
		if (first == 1) {
			crushFriends.push_back(second);
			crushEdges.push_back(make_pair(l, r));
		} else {
			friends[first] = second;
			edges[first][0] = l;
			edges[first][1] = r;
		}
	}

	int count = crushFriends.size();
	int changes[count][SECRETS + 1][SECRETS + 1];

	for (int i = 0; i < count; i++) {
		for (int j = 1; j < SECRETS + 1; j++)
			for (int k = j; k < SECRETS + 1; k++)
				changes[i][j][k] = 0;
	}

	for (int i = 0; i < count; i++) {
		int src = crushFriends[i];
		for (int j = 1; j <= SECRETS; j++) {
			for (int k = j; k <= SECRETS; k++) {
				if (crushEdges[i].first > j)
					changes[i][j][k] += crushEdges[i].first - j;
				if (crushEdges[i].second < k)
					changes[i][j][k] += k - crushEdges[i].second;

			}
		}

		while (friends[src] != -1) {
			for (int j = 1; j <= SECRETS; j++) {
				for (int k = j; k <= SECRETS; k++) {
					if (edges[src][0] > j)
						changes[i][j][k] += edges[src][0] - j;
					if (edges[src][1] < k)
						changes[i][j][k] += k - edges[src][1];
				}
			}
			src = friends[src];
		}

		// for (int j = 1; j <= SECRETS; j++) {
		// 	for (int k = j; k <= SECRETS; k++) {
		// 		cout << i << " " << j << " " << k;
		// 		cout << " " << changes[i][j][k] << endl;
		// 	}
		// }
	}

	cout << changes[0][1][9] + changes[1][10][32] << endl;

	// int dp[SECRETS + 1][SECRETS + 1];
	// for (int i = 0; i < SECRETS + 1; i++) {
	// 	for (int j = 0; j < SECRETS + 1; j++)
	// 		dp[i][j] = INT_MAX;
	// }

	// dp[1][0] = 1;
	// for (int i = 1; i < SECRETS + 1; i++) {
	// 	int increment = INT_MAX;
	// 	for (int j = 0; j < count; j++) {
	// 		increment 
	// 	}
	// }
}