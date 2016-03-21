#include <stdio.h>
#include <algorithm>
#include <string.h>
#include <vector>

using namespace std;

const int MAX = 4000;
const int MOD = (1 << 30);
vector<int> primes;
vector<int> num;

int findGcd(int a, int b)
{
	int temp;
	while (b != 0) {
		temp = b;
		b = b % a;
		a = temp;
	}
	return a;
}

int findLcm(int a, int b)
{
	int gcd = findGcd(a, b);
	return a * b / gcd;
}

int binarySearch(int limit)
{
	if (limit < num[0])
		return -1;
	int low = 0, high = num.size() - 1, mid;
	while (low < high) {
		mid = low + ((high - low + 1) >> 1);
		if (num[mid] > limit) high = mid - 1;
		else low = mid;
	}
	return low;
}

int main()
{
	bool isPrime[MAX + 1];
	memset(isPrime, true, sizeof(isPrime[0]) * (MAX + 1));

	for (int i = 2; i * i <= MAX; i++) {
		if (isPrime[i]) {
			for (int j = i + i; j <= MAX; j += i)
				isPrime[j] = false;
		}
	}

	for (int i = 2; i <= MAX; i++) {
		if (isPrime[i])
			primes.push_back(i);
	}

	num.push_back(1);
	for (int prime : primes) {
		int length = num.size(), value;
		for (int i = 0; i < length; i++) {
			value = num[i] * prime;
			if (value <= MAX)
				num.push_back(value);
		}
	}

	sort(num.begin(), num.end());

	int t, L, R;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++) {
		scanf("%d %d", &L, &R);
		int leftBound = binarySearch(L);
		int rightBound = binarySearch(R);

		int result = 0;
		for (int i = 0; i <= leftBound; i++) {
			for (int j = 0; j <= rightBound; j++) 
				result = (result + findLcm(num[i], num[j])) % MOD;
		}

		printf("%d\n", result);
	}
}