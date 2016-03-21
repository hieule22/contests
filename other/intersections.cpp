#include <iostream>
#include <unordered_map>
#include <map>
#include <vector>

using namespace std;

struct pair_hash {
public:
	size_t operator() (const pair<double, double> &x) const
	{
		return hash<double>()(x.first) ^ hash<double>()(x.second);
	}
};

int main()
{
	ios_base::sync_with_stdio(false);
	int n, k;
	cin >> n >> k;
	unordered_map<int, vector<pair<int,int> > > gradientMap;
	unordered_map<pair<double,double>, int, pair_hash> points;

	int m, c;
	double x, y;
	for (int i = 0; i < n; i++) {
		cin >> m >> c;
		for (auto entry : gradientMap)
		{
			if (entry.first != m) {
				for (auto line : entry.second) {
					x = (line.second - c) * 1.0 / (m - line.first);
					y = m * x + c;
					pair<double, double> point = make_pair(x, y);
					points[point]++;
				}
			}
		}
		gradientMap[m].push_back(make_pair(m, c));
	}second

	map<double, int> heights;
	for (auto entry : points)
		heights[entry.first.second] = entry.second;
	int soFar = 0;
	for (auto entry : heights) {
		soFar += entry.second;
		if (soFar >= k) {
			cout << entry.first << endl;
			break;
		}
	}
}