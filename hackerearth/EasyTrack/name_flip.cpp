#include <iostream>
#include <string>
#include <algorithm>

using namespace std;


void solve()
{
	string s;	
	cin >> s;
	int res = 0, suffix = 0, cnt = 0;
	for (int i = 0; i < s.length(); i++) {
		if (s[i] == 'K')
			suffix++;
		else {
			cnt++;
			suffix--;
			if (suffix < 0) 
				suffix = 0;
		}
		res = max(suffix, res);
	}
	if (cnt == s.length()) 
		res = -1;
	cout << cnt + res << endl;
}

int main()
{
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		solve();
	}
}

