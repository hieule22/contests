#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
	int n;
	cin >> n;
	int color[n + 1];
	for (int i = 1; i <= n; i++)
		cin >> color[i];

	int parent[n + 1];
	for (int i = 2; i <= n; i++)
		cin >> parent[i];

	int first[n + 1], second[n + 1];
	for (int i = 1; i <= n; i++) {
		first[i] = second[i] = 0;
	}

	int res = 0;
	for (int i = n; i > 1; i--) {
		if (color[i] == 1) {
			int across = first[i] + second[i] + 1;
			res = max(res, across);
			int length = max(first[i], second[i]) + 1;
			if (length > first[parent[i]]) {
				second[parent[i]] = first[parent[i]];
				first[parent[i]] = length;
			} else if (length > second[parent[i]]) {
				second[parent[i]] = length;
			}
		}
	}

	if (color[1] == 1) {
		int across = first[1] + second[1] + 1;
		res = max(across, res);
	}

	cout << res << endl;
}