#include <iostream>

using namespace std;

int main()
{
	uint t;
	cin >> t;

	for (uint tt = 0; tt < t; tt++)
	{
		uint n, m, s;
		cin >> n >> m >> s;
		uint last = s + m - 1;
		last = last % n;
		if (last == 0)
			last = n;
		cout << last << endl;
	}
}