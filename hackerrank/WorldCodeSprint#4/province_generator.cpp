#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

int main() {
	int p = 15, n = 600;
	cout << p << endl;

	srand(time(NULL));

	for (int i = 0; i < p; i++) {
		cout << n << endl;
		for (int j = 0; j < n; j++) {
			cout << (char)('a' + rand() % 26);
		}
		cout << endl;
		for (int j = 0; j < n; j++) {
			cout << (char)('a' + rand() % 26);
		}
		cout << endl;
	}
}