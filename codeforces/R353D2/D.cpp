#include <iostream>
#include <set>
#define MAX 1000000009

using namespace std;

struct Interval {
	int begin, data;

	Interval(int b, int p) 
		: begin(b), data(p) {};
};

bool operator < (const Interval & lhs, const Interval & rhs)
{
	return lhs.begin < rhs.begin;
}

int main()
{
	int n;
	cin >> n;
	set<Interval> s;
	s.insert(Interval(0, 0));

	int value, res[n];
	
	for (int i = 0; i < n; i++) {
		cin >> value;
		Interval temp(value, 0);
		auto iter = s.upper_bound(temp);

		--iter;
		Interval cur = *(iter);
		s.erase(iter);
		res[i] = cur.data;
		Interval low(cur.begin, value);
		Interval high(value, value);
		s.insert(low);
		s.insert(high);
	}

	for (int i = 1; i < n; i++)
		cout << res[i] << " ";
	cout << endl;
}