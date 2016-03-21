#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_map>

using namespace std;

const int MAX_N = 5 * 1e5 + 5;
int fre[MAX_N];
int a[MAX_N], n, m;

void compress(int a[], int n)
{
	vector<int> temp;
	for (int i = 1; i <= n; i++)
		temp.push_back(a[i]);
	sort(temp.begin(), temp.end());
	temp.resize(unique(temp.begin(), temp.end()) - temp.begin());
	for (int i = 1; i <= n; i++)
		a[i] = lower_bound(temp.begin(), temp.end(), a[i]) - temp.begin();
}

int getSum(int BITree[], int index)
{
	int sum = 0;
	while (index > 0) {
		sum += BITree[index];
		index -= index & (-index);
	}
	return sum;
}

void updateBIT(int BITree[], int n, int index, int val)
{
	while (index <= n)
	{
		BITree[index] += val;
		index += index & (-index);
	}
}

int countInversions(int arr[], int n)
{
	int count = 0;
	int maxElement = 0;
	for (int i = 1; i <= n; i++)
		maxElement = max(maxElement, arr[i]);

	cout << maxElement << endl;

	int BIT[maxElement + 1];
	for (int i = 1; i <= maxElement; i++)
		BIT[i] = 0;

	for (int i = n; i > 0; i--) {
		count += getSum(BIT, arr[i] - 1);
		updateBIT(BIT, maxElement, arr[i], 1);
	}

	return count;
}

int main()
{
	cin >> n;
	m = (int)sqrt(n);

	for (int i = 1; i <= n; i++)
		cin >> a[i];

	compress(a, n);

	for (int i = 1; i <= n; i++)
		fre[a[i]]++;

	int res = 0;
	for (int i = 1; i <= n; i++) {
		unordered_map<int, int> countMap;
		int maxCount = 0, maxValue;
		for (int j = i; j <= min(n, i + 2 * m); ++j) {
			countMap[a[j]]++;
			if (countMap[a[j]] > maxCount) {
				maxCount = a[j];
				maxValue = a[j];
			}
			if (maxCount > (j - i + 1) / 2 && fre[maxValue] <= m)
				res++;
		}
	}

	int s[n + 1], b[n + 1];
	for (int x = 0; x <= n; x++) {
		if (fre[x] > m) {
			s[0] = b[0] = 0;
			for (int i = 1; i <= n; i++) {
				s[i] = s[i - 1];
				if (a[i] == x)
					s[i]++;
				b[i] = 2 * s[i] - i;
			}
			res += countInversions(b, n);
		}
	}
}