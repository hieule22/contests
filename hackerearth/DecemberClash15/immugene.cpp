#include <string>
#include <iostream>
#include <unordered_map>
#include <set>
using namespace std;
typedef unsigned long long uint64;
const uint64 MOD = 1000 * 1000 * 1000 + 7;

class FenwichTree 
{
public:
	FenwichTree(int n) : maxVal(n) {
		tree = new uint64[maxVal + 1];
		for (int i = 0; i <= maxVal)
			tree[i] = 0;
	}

	uint64 read(int index) {
		uint64 sum = 1;
		while (index > 0) {
			sum = (sum + tree[index]) % MOD;
			index -= (index & -index);
		}
		return sum;
	}

	void update(int index, int val) {
		while (index <= maxVal) {
			tree[index] = (tree[index] + val) % MOD;
			index += (index & -index);
		}
	}

private:
	int maxVal;
	uint64 *tree;
};

int main() 
{
	int n, q;
	string str;
	cin >> n >> q >> str;

	FenwichTree fw(n);
	unordered_map<char, int> last;
	unordered_map<char, set<int> > pos_set;
	for (int i = 1; i <= n; i++) {
		uint64 delta = fw.read(i - 1);
		if (last.find(str[i - 1]) != last.end())
			delta -= fw.read(last[str[i - 1]] - 1);
		fw.update(i, delta);
		last[str[i - 1]] = i;
		pos_set[str[i - 1]].insert(i);
	}

	int pos;
	char previous, next, set<;
	for (int i = 0; i < q; i++) {
		cin >> pos >> letter;
		previous = str[pos - 1];
		auto iter = 
	}

	// uint64 dp[n + 1], sum[n + 1];
	// dp[0] = sum[0] = 1;

	// for (int i = 1; i <= n; i++) {
	// 	dp[i] = sum[i - 1];
	// 	if (last.find(str[i - 1]) != last.end())
	// 		dp[i] -= sum[last[str[i - 1]] - 1];
	// 	sum[i] = sum[i - 1] + dp[i];
	// 	last[str[i - 1]] = i;
	// }

}