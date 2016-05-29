#include <iostream>
#define abs(x) ((x > 0) ? x : (-x))
using namespace std;

bool solve(int a, int b, int c)
{
	if (a == b) return true;
	if (c == 0) return false;

	int delta, step;
	if (c < 0) {
		if (b > a) return false;
		delta = a - b;
		step = abs(c);
	} else {
		if (b < a) return false;
		delta = b - a;
		step = abs(c);
	}

	return delta % step == 0;
}

int main()
{
	int a, b, c;
	cin >> a >> b >> c;

	if (solve(a, b, c))
		cout << "YES" << endl;
	else 
		cout << "NO" << endl;
}