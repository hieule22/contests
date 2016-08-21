#include <stdio.h>
#include <algorithm>
#include <vector>
using namespace std;
typedef pair<int, int> Point;
typedef pair<Point, Point> Segment;

struct VerticalSorter 
{
	bool operator() (const Segment &lhs, const Segment &rhs) const
	{
		return lhs.first.first < rhs.first.first;
	}
};

struct HorizontalSorter
{
	bool operator() (const Segment &lhs, const Segment &rhs) const
	{
		return lhs.first.second < rhs.first.second;
	}
}

int main()
{
	int n;
	cin >> n;
	vector<Segment> vt, hz, segments;

	int x1, y1, x2, y2;
	Point begin, end;
	for (int i = 0; i < n; i++) {
		scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
		begin = make_pair(x1, y1);
		end = make_pair(x2, y2);
		// Check for vertical line
		if (x1 == x2) {
			if (y1 > y2)
				swap(begin, end);
			vt.push_back(make_pair(begin, end));
		} else {
			if (x1 > x2)
				swap(begin, end);
			hz.push_back(make_pair(begin, end));
		}
		segments.push_back(make_pair(begin, end));
	}
	sort(vt.begin(), vt,end(), VerticalSorter());
	sort(hz.begin(), hz.end(), HorizontalSorter());

	int total = 0;
	for (int i = 0; i < vt.size(); i++) {
		total += abs(vt[i].first.second - vt[i].second.second);
		int j = i - 1;
		while (j >= 0) {
			if (vt[i].first.first != vt[j].first.first)
				break;
			if (vt[j].second.second < vt[i].first.second)
				break;
			
		}
	}
}