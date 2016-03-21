#include <string>
#include <algorithm>
#include <iostream>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	string s;
	cin >> s;
	
	int total = 1;
	char cur = s[0];
	int couples = 0;
	for (int i = 1; i < n; i++)
	{
		if (s[i] != cur) {
			total++;
			cur = s[i];
		}
		if (s[i] == s[i - 1])
			couples++;
	}
	total += min(2, couples);
	cout << total << endl;
}