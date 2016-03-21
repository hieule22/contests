#include <iostream>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n, m;
	cin >> n >> m;
	int a[m + 1];
	for (int i = 0; i < m + 1; i++) 
		a[i] = 0;
	int genre;
	for (int i = 0; i < n; i++) {
		cin >> genre;
		a[genre]++;
	}

	int total = 0;
	for (int i = 1; i < m + 1; i++) {
		for (int j = i + 1; j < m + 1; j++)
			total += a[i] * a[j];
	}

	cout << total << endl;
}