#include <vector>
#include <iostream>

using namespace std;
const int MOD = (int)1e9 + 7;

int main()
{
	int array[] = {1, 1, 1, 1, 1, 9, 17, 25, 57, 113};
	vector<int> a;
	for (int i = 0; i < 10; i++)
		a.push_back(array[i]);

	for (int i = 0; i < 500; i++) {
		int num = 4;
		for (int j = a.size() - 1; j >= a.size() - 5; j--)
			num = (num + a[j]) % MOD;
		a.push_back(num);
	}

	int n, m;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> m;
		cout << a[m - 1] << endl;
	}
}