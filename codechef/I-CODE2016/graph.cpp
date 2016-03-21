#include <iostream>
#include <string.h>

using namespace std;

int cellIndex(const int * a, int l, int r, int key)
{
	while (r - l > 1)
	{
		int m = l + (r - l) / 2;
		if (a[m] >= key)
			r = m;
		else
			l = m;
	}
	return r;
}

int maxSubsequence(const int * a, int size)
{
	int tailTable[size];
	int len;

	memset(tailTable, 0, sizeof(tailTable[0]) * size);

	tailTable[0] = a[0];
	len = 1;
	for (int i = 1; i < size; i++) 
	{
		if (a[i] < tailTable[0])
			tailTable[0] = a[i];
		else if (a[i] > tailTable[len - 1])
			tailTable[len++] = a[i];
		else
			tailTable[cellIndex(tailTable, -1, len - 1, a[i])] = a[i];
	}

	return len;
}

int main()
{
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;

	for (int tt = 0; tt < t; tt++)
	{
		int n;
		cin >> n;
		int a[n];
		for (int i = 0; i < n; i++)
			cin >> a[i];
		cout << maxSubsequence(a, n) << endl;
	}
}