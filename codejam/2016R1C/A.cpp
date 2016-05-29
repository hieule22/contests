#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

bool pairComp(const pair<int, int> & lhs, const pair<int, int> & rhs)
{
	return lhs.second < rhs.second;
}

void solve(int test)
{
	int n; cin >> n;
	vector<pair<int, int> > parties;
	int p;
	for (int i = 0; i < n; i++) {
		cin >> p;
		parties.push_back(make_pair(i, p));
	}

	sort(parties.begin(), parties.end(), pairComp);

	cout << "Case #" << test << ":";

	// Print the dominant party members
	int delta = (parties.end() - 1)->second - (parties.end() - 2)->second;
	for (int i = 0; i < delta; i++)
		cout << " " << (char)('A' + (parties.end() - 1)->first);

	for (auto iter = parties.begin(); iter < parties.end() - 2; ++iter) {
		for (int i = 0; i < iter->second; i++)
			cout << " " << (char)('A' + iter->first);
	}

	auto iter = parties.end() - 2;
	for (int i = 0; i < iter->second; i++)
		cout << " " << (char)('A' + iter->first) << (char)('A' + (parties.end() - 1)->first);
	cout << endl;
}

int main()
{
	int tt;
	cin >> tt;
	for (int t = 1; t <= tt; t++)
		solve(t);
}