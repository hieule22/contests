#include <iostream>
#define max(a) (a < 0 ? -a : a)
using namespace std;

int main()
{
	int n;
	cin >> n;
	int position[n + 1];
	int fragment;
	for (int i = 1; i <= n; i++) {
		cin >> fragment;
		position[fragment] = i;
	}

	unsigned long long total = 0;
	for (int i = 2; i <= n; i++) {
		total += abs(position[i] - position[i - 1]);
	}

	cout << total << endl;
}