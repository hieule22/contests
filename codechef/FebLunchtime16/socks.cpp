#include <iostream>

using namespace std;
typedef unsigned uint;

int main()
{
	uint jacket, sock, money;
	cin >> jacket >> sock >> money;

	uint cnt = (money - jacket) / sock;

	if (cnt & 1)
		cout << "Unlucky Chef" << endl;
	else
		cout << "Lucky Chef" << endl;
}