#include <algorithm>
#include <iostream>
using namespace std;

int main() 
{
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		string s;
		cin >> s;
		int x = 0;
		char cur = 'Y';
		for (int i = 0; i < s.length(); i++) {
			if (s[i] == cur) {
				x++;
			} else {
				cur = s[i];
			}
		}

		int y = 0;
		cur = 'X';
		for (int i = 0; i < s.length(); i++) {
			if (s[i] == cur) {
				y++;
			} else {
				cur = s[i];
			}
		}

		cout << min(x, y) << endl;
	}
}