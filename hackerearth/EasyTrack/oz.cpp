#include <algorithm>
#include <iostream>
#include <string>
using namespace std;

int main()
{
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		string s;
		cin >> s;
		int cnt_o = 0, cnt_z = 0;
		int inv_o = 0, inv_z = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s[i] == 'O') {
				cnt_o++;
				inv_o += cnt_z;
			} else {
				cnt_z++;
				inv_z += cnt_o;
			}
		}

		cout << min(inv_o, inv_z) << endl;
	}
}