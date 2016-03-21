#include <string.h>
#include <iostream>
#define MAX 105
#define min(a, b) ((a < b) ? a : b)

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	int t, n, len, a, b;
	cin >> t;
	char str[MAX];
	while (t--) {
		cin >> n;
		len = 0;
		a = b = MAX;
		for (int i = 0; i < n; i++) {
			cin >> str;
			if (!len) len = strlen(str);
			int cA = 0, cB = 0;
			for (int j = 0; j < len; j++) {
				if (str[j] == 'a') cA++;
				else cB++;
			}
			a = min(a, cA);
			b = min(b, cB);
		}

		cout << min(a, b) << endl;
	}
}