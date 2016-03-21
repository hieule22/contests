#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

const int MAX = 10005;
int m, n;

void solve() {
	cin >> m >> n;
	vector<int> boys, girls;
	int height;
	for (int i = 0; i < m; i++) {
		cin >> height;
		boys.push_back(height);
	}
	for (int i = 0; i < n; i++) {
		cin >> height;
		girls.push_back(height);
	}

	if (m > n) {
		cout << "NO" << endl;
		return;
	}

	sort(boys.begin(), boys.end());
	sort(girls.begin(), girls.end());

	int ptr = 0;
	for (int i = 0; i < m; i++) {
		if (girls[i] >= boys[i]) {
			cout << "NO" << endl;
			return;
		}
	}

	cout << "YES" << endl;
}

int main() 
{
	int t;
	cin >> t;
	for (int i = 0; i < t; i++)
		solve();
}