#include <iostream>
#define max(a, b) ((a > b) ? a : b)

using namespace std;
const int MAX_N = (1 << 14);
int n, a[MAX_N + 1], prefix[MAX_N + 1];

int split(int begin, int end)
{
	cout << begin << " " << end << endl;
	if (begin >= end) 
		return 0;
	int sum = prefix[end] - prefix[begin - 1];
	if (sum & 2)
		return 0;
	// cout << sum << endl;

	int target = (sum >> 1);
	int low = begin, high = end, mid;
	while (low < high) {
		mid = low + ((high - low) >> 1);
		if (prefix[mid] - prefix[begin - 1] >= target)
			high = mid;
		else
			low = mid + 1;
	}

	if (prefix[low] - prefix[begin - 1] != target)
		return 0;
	// cout << begin << " " << low << " " << end << endl;
	return 1 + max(split(begin, low), split(low + 1, end));
}

int main()
{
	int t;
	ios_base::sync_with_stdio(false);
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		cin >> n;
		prefix[0] = 0;
		for (int i = 1; i <= n; i++) {
			cin >> a[i];
			prefix[i] = a[i] + prefix[i - 1];
		}

		cout << split(1, n) << endl;
	}
}