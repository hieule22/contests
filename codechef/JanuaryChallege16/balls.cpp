#include <stdio.h>
#include <string.h>
#include <vector>
#include <climits>
using namespace std;

const int MAX = 10000;
int prime[MAX + 1], a[MAX + 1], n;
vector<int> primes;

int main()
{
	memset(prime, 1, sizeof(prime));
	for (int i = 2; i * i <= MAX; i++) {
		if (prime[i]) {
			for (int j = i * 2; j <= MAX; j += i) 
				prime[j] = 0;
		}
	}

	for (int i = 2; i <= MAX; i++) {
		if (prime[i])
			primes.push_back(i);
	}

	int t;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++)
	{
		scanf("%d", &n);
		for (int i = 0; i < n; i++) 
			scanf("%d", a + i);
		
		int res = INT_MAX, remainder, cur, last;
		for (int divisor : primes) {
			cur = last = 0;
			for (int i = 0; i < n; i++) {
				if (a[i] < last) {
					cur += (last - a[i]);
				} else {
					remainder = a[i] % divisor;
					if (remainder) {
						cur += (divisor - remainder);
						last = a[i] + (divisor - remainder);
					} else {
						last = a[i];
					}
				}
			}
			if (res > cur)
				res = cur;
		}

		printf("%d\n", res);
	}


}