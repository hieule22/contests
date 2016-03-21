#include <iostream>
using namespace std;

int main()
{
	int test;
	cin >> test;
	for (int tt = 0; tt < test; tt++)
	{
		int n;
		cin >> n;
		int mod[3];
		for (int i = 0; i < 3; i++) mod[i] = 0;

		int num, rem;
		for (int i = 0; i < n; i++) {
			cin >> num;
			rem = num % 3;
			mod[rem] = 1 - mod[rem];
		}

		if (mod[1] || mod[2])
			cout << "Balsa" << endl;
		else
			cout << "Koca" << endl;
	}
}