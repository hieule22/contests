#include <iostream>
using namespace std;

int main()
{
	int n;
	cin >> n;
	int cur = 1, time;
	for (int i = 0; i < n; i++) {
		cin >> time;
		if (cur < time) cur = time;
		cur++;
	}
	cur--;
	cout << cur << endl;
}