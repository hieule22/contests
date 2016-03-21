#include <iostream>
using namespace std;

int main()
{
	int n, p, q;
	char s[105];
	cin >> n >> p >> q >> s;

	int a;
	for (a = 0; a <= n / p; a++)
	{
		if ((n - a * p) % q == 0) {
			break;
		}
	}

	if (a > n / p) {
		cout << -1 << endl;
	} else {
		int b = (n - a * p) / q;
		cout << a + b << endl;
		int index = 0;
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < p; j++)
				cout << s[index++];
			cout << endl;
		}

		for (int i = 0; i < b; i++) {
			for (int j = 0; j < q; j++)
				cout << s[index++];
			cout << endl;
		}
	}
}