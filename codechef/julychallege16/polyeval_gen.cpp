#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

int main() {
	int n = 100000;
	int MAX = 786433;

	cout << n << endl;
	int a;
	srand(time(NULL));
	for (int i = 0; i <= n; ++i) {
		a = rand() % MAX;
		cout << a << " ";
	}
	cout << endl << n << endl;
	for (int i = 0; i < n; ++i) {
		a = rand() % MAX;
		cout << a << endl;
	}
}