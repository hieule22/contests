#include <iostream>

using namespace std;
typedef long long int64;

int main()
{
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	int L[n + 1], R[n + 1], cnt[n + 2];
	for (int i = 0; i < n + 2; i++) 
		cnt[i] = 0;

	int64 total = 0;
	for (int i = 1; i <= n; i++) {
		cin >> L[i] >> R[i];
		cnt[L[i]]++;
		cnt[R[i] + 1]--;
		total += (R[i] - L[i] + 1);
	}

	for (int i = 1; i <= n; i++)
		cnt[i] += cnt[i - 1];

	int q, row, col;
	int64 res;
	cin >> q;
	for (int i = 0; i < q; i++) {
		cin >> row >> col;
		res = (R[row] - L[row] + 1) + cnt[col];
		if (col >= L[row] && col <= R[row])
			res--;
		res = total - res;
		cout << ((res % 2) ? "O" : "E") << endl;
	}
}