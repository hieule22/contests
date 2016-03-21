#include <iostream>
#include <algorithm>
#include <string.h>
#include <climits>

using namespace std;
const int MAX = 1000;

int main()
{
	int n;
	cin >> n;

	int a[MAX + 1];
	int b[MAX + 1];

	memset(a, 0, sizeof(a[0]) * (MAX + 1));
	memset(b, 0, sizeof(b[0]) * (MAX + 1));

	int num;
	for (int i = 0; i < n; i++) {
		cin >> num;
		a[num]++;
	}
	for (int i = 0; i < n; i++) {
		cin >> num;
		b[num]++;
	}

	int total = 0;
	bool more = false, less = false;
	for (int i = 0; i <= MAX; i++) {
		total += min(a[i], b[i]);
		more |= (a[i] > b[i]);
		less |= (a[i] < b[i]);
	}

	if (more && less)
		total++;
	else
		total--;

	cout << total << endl;
}