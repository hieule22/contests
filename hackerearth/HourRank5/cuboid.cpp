#include <iostream>

using namespace std;

int main()
{
	int sum;
	cin >> sum;

	int res = 0;
	for (int i = 1; i <= sum; i++) {
		for (int j = i; j <= sum - i; j++) {
			res = max(res, i * j * (sum - i - j));
		}
	}

	cout << res << endl;
}