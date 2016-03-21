#include <vector>
#include <iostream>
#include <math.h>
#include <string.h>

#define MAX 32005

using namespace std;

bool prime[MAX];

int main() {
	ios_base::sync_with_stdio(false);
	memset(prime, true, sizeof(prime));
	prime[0] = prime[1] = false;
	for (int i = 2; i * i <= MAX; i++) {
		if (prime[i]) {
			for (int j = (i << 1); j < MAX; j += i)
				prime[j] = false;
		}
	}

	int n, x;
	cin >> n;
	while (n--) {
		cin >> x;
		vector<pair<int, int> > primes;
		for (int i = 2; i <= (x >> 1); i++) {
			if (prime[i] && prime[x - i])
				primes.push_back(make_pair(i, x - i));
		}

		cout << x << " has " << primes.size() << " representation(s)" << endl;
		for (auto iter = primes.begin(); iter != primes.end(); iter++)
			cout << iter->first << "+" << iter->second << endl;
		cout << endl;
	}
}
