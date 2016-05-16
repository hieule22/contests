#include <iostream>
#include <unordered_map> 
#include <algorithm>

using namespace std;
typedef unsigned long long uint64;
const uint64 MOD = 1e9 + 7;
const uint64 MAX = 1e9;
uint64 fib[100], n;

struct pairhash 
{
	template <typename T, typename U>
	size_t operator() (const pair<T, U> & x) const
	{
		return hash<T>()(x.first) ^ hash<U>()(x.second);
	}
};

uint64 recurse(uint64 sum, int times, int index, 
	unordered_map<uint64, unordered_map<pair<int, int>, uint64, pairhash> > & memo)
{
	if (times == 0) {
		if (sum == 0) return 1;
		else return 0;	
	}

	if (index < 0) return 0;

	if (fib[index] * times < sum) return 0;

	if (memo.find(sum) != memo.end() && memo[sum].find(make_pair(times, index)) != memo[sum].end())
		return memo[sum][make_pair(times, index)];
	
	uint64 result;
	if (fib[index] > sum)
		result =  recurse(sum, times, index - 1, memo);
	else {
		result = (recurse(sum - fib[index], times - 1, index, memo) + 
			recurse(sum, times, index - 1, memo)) % MOD;
	}
	memo[sum][make_pair(times, index)] = result;
	return result;
}

int main()
{
	ios_base::sync_with_stdio(false);
	int q; cin >> q;
	uint64 sum;
	int k;
	fib[0] = 1; fib[1] = 2; n = 2;
	while (fib[n - 1] <= MAX) {
		fib[n] = fib[n - 1] + fib[n - 2];
		n++;
	}

	for (int qq = 0; qq < q; qq++) {
		cin >> sum >> k;
		unordered_map<uint64, unordered_map<pair<int, int>, uint64, pairhash> > memo;
		int index = lower_bound(fib, fib + n - 1, sum) - fib;

		uint64 result = recurse(sum, k, index, memo);
		cout << result << endl;	
	}
}