#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

typedef pair<int, int> Beacon;

struct BeaconSort 
{
	bool operator() (const Beacon &lhs, const Beacon &rhs) const
	{
		return lhs.first < rhs.first;
	}
};

int main()
{
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	vector<Beacon> beacons;
	int position, power;
	for (int i = 0; i < n; i++) {
		cin >> position >> power;
		beacons.push_back(make_pair(position, power));
	}
	sort(beacons.begin(), beacons.end(), BeaconSort());

	int cnt[n];
	cnt[0] = 1;
	int low, high, mid;
	int res = n - 1;
	for (int i = 1; i < n; i++) {
		if (beacons[0].first >= beacons[i].first - beacons[i].second) {
			cnt[i] = 1;
		} else {
			low = 0;
			high = i - 1;
			while (low < high) {
				mid = low + ((high - low + 1) >> 1);
				if (beacons[mid].first >= beacons[i].first - beacons[i].second) 
					high = mid - 1;
				else
					low = mid;
			}
			cnt[i] = 1 + cnt[low];
		}
		res = min(res, n - cnt[i]);
	}

	cout << res << endl;
}