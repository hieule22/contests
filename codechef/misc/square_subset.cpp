#include <unordered_map>
#include <iostream>
#include <vector>
using namespace std;
typedef unsigned long long uint64;

const int MAX_PRIME = 500;
vector<int> primes;
unordered_map<int, int> prime_index;
int is_prime[MAX_PRIME + 1], prime_count;

void solve()
{
	int n;
	cin >> n;
	unordered_map<vector<int>, uint64> factors;
}

int main()
{
	memset(is_prime, 1, MAX_PRIME * sizeof(int));

	is_prime[0] = is_prime[1] = 0;
	for (int i = 2; i <= MAX_PRIME; i++) {
		if (is_prime[i]) {
			primes.push_back(i);
			for (int j = i + i; j <= MAX_PRIME; j += i) {
				is_prime[i] = 0;
			}
		}
	}

	prime_count = primes.size();
	for (int i = 0; i < prime_count; i++)
		prime_index[primes[i]] = i;

	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++)
		solve();
}
