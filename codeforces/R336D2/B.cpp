#include <iostream>
#include <string>
#define max(a, b) ((a > b) ? a : b)
#define min(a, b) ((a < b) ? a : b)


using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	string a, b;
	cin >> a >> b;
	int lenA = a.size();
	int lenB = b.size();
	// cout << a.size() << " " << b.size() << endl;
	int prefix[lenA];
	prefix[0] = (a[0] == '1') ? 1 : 0;
	for (int i = 1; i < lenA; i++)
		prefix[i] = prefix[i - 1] + ((a[i] == '1') ? 1 : 0);

	unsigned long long total = 0;
	int left, right, count;
	for (int i = 0; i < lenB; i++) {
		left = max(0, i - (lenB - lenA));
		right = min(lenA - 1, i);

		// cout << left << " " << right << endl;

		if (left == 0)
			count = prefix[right];
		else
			count = prefix[right] - prefix[left - 1];

		if (b[i] == '1') {
			total += (right - left + 1 - count);
		} else {
			total += count;
		}
	}

	cout << total << endl;
}