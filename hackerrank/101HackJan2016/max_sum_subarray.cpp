#include <iostream>
using namespace std;

int main()
{
	int n;
	cin >> n;

	int max = 0, suffix = 0;
	int a;
	for (int i = 0; i < n; i++)
	{
		cin >> a;
		if (suffix > max)
			max = suffix;
		if (a == 0 || suffix + a < 0)
		{
			suffix = 0;
		} 
		else
		{
			suffix += a;
		}
	}

	if (suffix > max)
		max = suffix;

	cout << max << endl;
}