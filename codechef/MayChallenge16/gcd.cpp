#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
typedef long long int64;
const int64 MOD = 1e9 + 7;
int64 fact[100001];
vector<int> primes;
int n, m;
int primeCount, smallPrimeCount, largePrimeCount;

bool isPrime(int);
bool isSubset(int, int);
int compute(int, int);
int64 power(int64, int64);
int64 choose(int, int);

void solve()
{
	cin >> n >> m;
	primeCount = upper_bound(primes.begin(), primes.end(), m) - primes.begin();
	smallPrimeCount = min(4, primeCount);
	largePrimeCount = primeCount - smallPrimeCount;

	int64 memo[min(n, primeCount) + 1][largePrimeCount + 1][1 << smallPrimeCount];
	for (int i = 0; i <= min(n, primeCount); i++)
		for (int j = 0; j <= largePrimeCount; j++)
			for (int k = 0; k < (1 << smallPrimeCount); k++)
				memo[i][j][k] = 0;
	for (int i = 0; i <= largePrimeCount; i++)
		memo[0][i][0] = 1;

	int64 result = 1;
	for (int length = 1; length < min(n, primeCount) + 1; length++) {
		// No large prime
		memo[length][0][0] = 0;

		for (int i = 1; i < (1 << smallPrimeCount); i++) {
			int64 total = 0;
			for (int mask = 1; mask <= i; mask++) {
				if (isSubset(mask, i)) {
					int64 temp = compute(1, mask);
					total = (total + temp * memo[length - 1][0][i - mask]);
				}
			}
			total = total * power(length, MOD - 2) % MOD;
			memo[length][0][i] = total;
		}
		// Include large primes
		for (int i = 1; i < largePrimeCount + 1; i++) {
			memo[length][i][0] = choose(i, length);
			for (int j = 1; j < (1 << smallPrimeCount); j++) {
				int64 total = memo[length][i - 1][j];
				for (int mask = 0; mask <= j; mask++) {
					if (isSubset(mask, j)) {
						int64 temp = compute(primes[i + smallPrimeCount - 1], mask);
						total = (total + temp * memo[length - 1][i - 1][j - mask]);
					}
				}
				memo[length][i][j] = total;
			}
		}


		int64 sum = 0;
		for (int i = 0; i < (1 << smallPrimeCount); i++)
			sum = (sum + memo[length][largePrimeCount][i]) % MOD;
		sum = sum * fact[n] % MOD;
		result = (result + sum * power(fact[n - length], MOD - 2)) % MOD;
	}

	cout << result << endl;
}

int compute(int largePrime, int smallMask)
{
	vector<int> smallPrimes;
	int product = largePrime;
	for (int i = 0; i < smallPrimeCount; i++) {
		if (smallMask & (1 << i)) {
			product *= primes[i];
			smallPrimes.push_back(primes[i]);
		}
	}

	int ans = 0;
	for (int i = product; i <= m; i += product) {
		int factor = i / product;
		for (int p : smallPrimes) 
			while (factor % p == 0)
				factor /= p;
		if (factor == 1) {
			ans++;
		}
	}

	return ans;
}

int main()
{
	fact[0] = 1;
	for (int i = 1; i < 100001; i++)
		fact[i] = fact[i - 1] * i % MOD;

	for (int i = 2; i < 101; i++)
		if (isPrime(i))
			primes.push_back(i);

	int tests; cin >> tests;
	for (int i = 0; i < tests; i++)
		solve();
}

int64 power(int64 a, int64 b)
{
	if (b == 0)
		return 1;
	int64 ans = power(a, b >> 1);
	ans = ans * ans % MOD;
	if (b & 1)
		ans = ans * a % MOD;
	return ans;
}

int64 choose(int n, int k)
{
	if (n < k)
		return 0;
	int64 ans = fact[n] * power(fact[k], MOD - 2) % MOD;
	ans = ans * power(fact[n - k], MOD - 2) % MOD;
	return ans;
}

bool isSubset(int subset, int superset)
{
	for (int i = 0; i < smallPrimeCount; i++) {
		int mask = (1 << i);
		if ((subset & mask) && !(superset & mask))
			return false;
	}
	return true;
}

bool isPrime(int a)
{
	int limit = sqrt(a);
	for (int i = 2; i <= limit; i++)
		if (a % i == 0)
			return false;
	return true;
}