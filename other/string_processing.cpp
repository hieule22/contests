#include <iostream>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	string s;
	for (int i = 0; i < t; i++)
	{
		cin >> s;
		int sum = 0;
		for (int j = 0; j < s.size(); j++) {
			if (s[j] >= '0' && s[j] <= '9')
				sum += (int)(s[j] - '0');
		}
		cout << sum << endl;
	}
}