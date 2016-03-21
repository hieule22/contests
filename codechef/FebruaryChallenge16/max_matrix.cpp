#include <stdio.h>
#include <stack>

using namespace std;
typedef long long int64;
const int64 MOD = (int64)1e9 + 7;

int64 * solve(const int *, int);

int main()
{
	int n;
	scanf("%d", &n);

	int a[n];
	for (int i = 0; i < n; i++) {
		scanf("%d", a + i);
		a[i] += i + 1;
	}

	int b[n];
	for (int i = 0; i < n; i++) {
		scanf("%d", b + i);
		b[i] += i + 1;
	}

	int64 * aCount = solve(a, n);
	int64 * bCount = solve(b, n);

	for (int i = 1; i <= n; i++)
		printf("%lld ", aCount[i] * bCount[i] % MOD);
	printf("\n");
}

int64 * solve(const int * a, int n)
{
	int64 * count = new int64[n + 2];
	for (int i = 0; i < n + 2; i++)
		count[i] = 0;

	stack<pair<int, int> > st;
	for (int i = 0; i < n; i++) {
		while (!st.empty()) {
			auto top = st.top();
			if (a[top.first] <= a[i]) {
				int left = 1, right = i - top.second;
				int64 comp = MOD - a[top.first];
				while (comp < 0) comp += MOD;
				int maxCount = min(top.first - top.second + 1, i - top.first);
				for (int j = 1; j <= maxCount; j++) {
					count[left] = (count[left] + a[top.first]) % MOD;
					count[right + 1] = (count[right + 1] + comp) % MOD;
					left++;
					right--;
				}
				st.pop();
			} else {
				st.push(make_pair(i, top.first + 1));
				break;
			}
		}
		if (st.empty())
			st.push(make_pair(i, 0));
	}

	// Flush the stack
	while (!st.empty()) {
		auto top = st.top();
		int left = 1, right = n - top.second;
		long comp = MOD - a[top.first];
		while (comp < 0) comp += MOD;
		int maxCount = min(top.first - top.second + 1, n - top.first);
		for (int i = 1; i <= maxCount; i++) {
			count[left] = (count[left] + a[top.first]) % MOD;
			count[right + 1] = (count[right + 1] + comp) % MOD;
			left++;
			right--; 
		}
		st.pop();
	}

	for (int i = 1; i < n + 1; i++)
		count[i] = (count[i - 1] + count[i]) % MOD;

	return count;
}