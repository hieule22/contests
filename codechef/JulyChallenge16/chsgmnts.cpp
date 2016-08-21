#include <iostream>
#include <unordered_map>
#include <deque>
#include <cstdint>
using namespace std;

int n, a[1000], suc[1000];

void Solve() {
	cin >> n;
	for (int i = 0; i < n; ++i) 
		cin >> a[i];

	unordered_map<int, int> cache;
	for (int i = n - 1; i >= 0; --i) {
		if (cache.find(a[i]) == cache.end())
			suc[i] = -1;
		else
			suc[i] = cache[a[i]];
		cache[a[i]] = i;
	}

	uint64_t res = 0;
	for (int left = 0; left < n - 1; ++left) {
		deque<pair<int, int> > seg;
		seg.push_back(make_pair(left + 1, n - 1));
		for (int right = left; right < n; ++right) {
			int cur = right;
			deque<pair<int, int> > temp;
			while (!seg.empty()) {
				auto& top = seg.front();
				if (top.first <= cur && cur <= top.second) {
					if (top.first <= cur - 1) 
						temp.push_back(make_pair(top.first, cur - 1));

					if (cur < top.second)
						top.first = cur + 1;
					else
						seg.pop_front();
				} else if (cur < top.first) {
					cur = suc[cur];
					if (cur == -1) {
						for (auto& s : seg)
							temp.push_back(s);
						break;
					}
				} else if (cur > top.second) {
					temp.push_back(top);
					seg.pop_front();
				}
			}
			for (auto& s : temp) {
				int length = s.second - s.first + 1;
				res += length * (length + 1) / 2;
			}
			seg = std::move(temp);
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