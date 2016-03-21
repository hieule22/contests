#include <iostream>
#include <string.h>
using namespace std;


int n, k, len[105];
char name[105];

int main() {
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		cin >> n >> k;
		for (int i = 0; i < 105; i++)
			len[i] = 0;
		for (int i = 0; i < n; i++) {
			cin >> name;
			len[strlen(name)]++;
		}

		bool isOk = true;
		for (int i = 0; i < 105; i++) {
			if (len[i] % k != 0) {
				isOk = false;
				break;
			}
		}

		cout << (isOk ? "Possible" : "Not possible") << endl;
	}
}
