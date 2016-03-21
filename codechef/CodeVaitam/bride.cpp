#include <iostream>
#include <deque>

using namespace std;

int main()
{
	int MAX = (int)1e6;
	int res[MAX + 1];
	res[1] = 1;
	res[2] = 2;

	for (int i = 3; i <= MAX; i++) {
		res[i] = res[i - 1] + 2;
		if (res[i] > i)
			res[i] = 2;
	}

	int t, n;
	ios_base::sync_with_stdio(false);
	cin >> t;
	for (int i = 0; i < t; i++) {
		cin >> n;
		cout << res[n] << endl;
	}
}