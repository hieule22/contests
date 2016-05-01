#include <iostream>
#include <unordered_map>
#include <string>
#include <vector>

using namespace std;

void solve(int testNumber)
{
	int n;
	cin >> n;
	vector<pair<string, string> > topics(n);
	unordered_map<string, int> first, second;
	for (int i = 0; i < n; i++) {
		cin >> topics[i].first >> topics[i].second;
		if (first.find(topics[i].first) == first.end())
			first[topics[i].first] = first.size();
		if (second.find(topics[i].second) == second.end())
			second[topics[i].second] = second.size();
	}

	bool graph[first.size()][second.size()];
	for (int i = 0; i < first.size(); i++)
		for (int j = 0; j < second.size())
}

int main()
{
	ios_base::sync_with_stdio(0);
	int testCount;
	cin >> testCount;
	for (int t = 1; t <= testCount; t++)
		solve(t);
}