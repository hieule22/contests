#include <iostream>
#include <string.h>
#include <cstdint>
#include <unordered_map>
#include <set>
#include <algorithm>
#define MAX_LEN 100005

using namespace std;
int arr[MAX_LEN], n;
unordered_map<int, int> rep;

struct BitSet
{
	static const int LENGTH = 1600;
	static const uint64_t ONE = 1;
	uint64_t mask[LENGTH];

	BitSet() {
		memset(mask, 0, sizeof mask);
	}

	void set(int n) {
		mask[n / 64] |= (ONE << (n % 64));
	}
};

void operator lor(const BitSet & lhs, const BitSet & rhs, BitSet & res)
{
	for (int i = 0; i < BitSet::LENGTH; i++)
		res.mask[i] = (lhs.mask[i] | rhs.mask[i]);
}

void compress()
{
	set<int> data;
	for (int i = 0; i < n; i++)
		data.insert(arr[i]);

	int index = 0;
	for (int entry : data) 
		rep[entry] = index++;

	for (int i = 0; i < n; i++)
		arr[i] = rep[arr[i]];
}

int main()
{
	int q;
	cin >> n >> q;
	for (int i = 0; i < n; i++)
		cin >> a[i];

	compress();

	int length = (int)sqrt(n);
	int count = n / length;
	BitSet sect[count];
	for (int i = 0; i < count; i++) {
		int begin = length * i;
		for (int j = begin; j < begin + length; j++)
			sect[i].set(arr[j]);
	}

	BitSet memo[count][count];
	for (int i = 0; i < count; i++) {
		memo[i][i] = sect[i];
		for (int j = i + 1; j < count; j++)
			lor(memo[i][j - 1], sect[j], memo[i][j]);
	}

	int res = 0, a, b, c, d, k;
	for (int qq = 0; qq < q; qq++) {
		cin >> a >> b >> c >> d >> k;
		int begin = (a * max(res, ))
	}
}