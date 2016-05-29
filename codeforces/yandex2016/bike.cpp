#include <iostream>
#include <cstdint>
#include <unordered_map>

using namespace std;

int n;
unordered_map<uint64_t, uint64_t> fre;
unordered_map<uint64_t, uint64_t> product; 

int main()
{
	ios_base::sync_with_stdio(0);
	cin >> n;
	uint64_t value;
	for (int i = 0; i < n; i++) {
		cin >> value;
		fre[value]++;
	}

	uint64_t ans = 0;

	uint64_t cnt = 0;
	for (auto & p : fre) {
		if (p.second > 1)
			cnt++;
		if (p.second > 3)
			ans++;
	}

	if (cnt > 1)
		ans += cnt * (cnt - 1) / 2;

	for (auto & p1 : fre) {
		for (auto & p2 : fre) {
			if (p2.first <= p1.first) continue;
			product[p1.first * p2.first]++;
		}
	}

	for (auto p : product)
		ans += p.second * (p.second - 1) / 2;



	cout << ans << endl;
}