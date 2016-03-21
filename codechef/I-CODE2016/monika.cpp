#include <vector>
#include <iostream>

using namespace std;
const int MAX = (int)1e9;

int main()
{
	bool isPrime[MAX + 1];
	memset(isPrime, true, MAX);

	isPrime[0] = isPrime[1] = false;
	for (int i = 2; i * i <= MAX; i++)
	{	
		if (isPrime[i]) {
			for (int j = i + i; j <= MAX; j += i)
				isPrime[j] = false;
		}
	}

	vector<int> primes;
	for (int i = 2; i <= MAX; i++) {
		if (isPrime[i])
			primes.push_back(i);
	}

	cout << primes.size() << endl;
}