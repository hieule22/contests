#include <string.h>
#include <iostream>
#define N 10005
using namespace std;

char s[N];
int main() {
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	while (t--) {
		cin >> s;
		int left = 0, right = 0;
		int i;
		for (i = 0; i < strlen(s); i++) {
			if (s[i] == 'B') left++;
			else break;
		}

		for (i = i + 1; i < strlen(s); i++) {
			if (s[i] == 'B') right++;
			else break;
		}
		cout << ((left == right) ? "Chef" : "Aleksa") << endl;
	}

}