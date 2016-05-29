#include <iostream>
#include <unordered_map>
#include <cstdint>
#include <algorithm>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(0);
	int n;
	cin >> n;
	unordered_map<int64_t, int> d;
	int64_t sum = 0;
	int ans = n - 1;
	for (int i = 0; i < n; i++) {
		int t;
		cin >> t;
		sum += t;
		d[sum]++;
	}

	for (auto & p : d) 
		ans = min(ans, n - p.second);

	cout << ans << endl;
}