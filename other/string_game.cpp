#include <iostream>
#include <string>
#include <vector>

using namespace std;

int k, length;
string s;
int *dp;

int count(int size)
{
	if (dp[size] >= 0) 
		return dp[size];
	if (size < k)
		dp[size] = 0;
	else {
		int remain = size - k;
		dp[size] = 1 + count(remain >> 1) + 
					count(remain - (remain >> 1));
	}
	return dp[size];
}

int main()
{
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) 
	{
		cin >> k >> s;
		length = s.size();
		dp = new int[length + 1];
		for (int i = 0; i <= length; i++)
			dp[i] = -1;

		vector<pair<int, int> > segments;
		int i = 0;
		char last = '0';
		while (i < length) {
			if (s[i] == '0' && last == '1') 
				segments[segments.size() - 1].second = i - 1;
			if (s[i] == '1' && last == '0')
				segments.push_back(make_pair(i, i));
			last = s[i];
			i++;
		}

		if (last == '1') 
			segments[segments.size() - 1].second = i - 1;

		int turns = 0;
		for (auto segment : segments)
			turns += count(segment.second - segment.first + 1);

		cout << ((turns & 1) ? "Alice" : "Bob") << "\n";
		delete[] dp;
	}
}