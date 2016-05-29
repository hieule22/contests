#include <stdio.h>
#include <vector>

using namespace std;
typedef long long int64;

int64 a[10]; 
int n;

int main()
{
	int tests; scanf("%d", &tests);
	for (int tt = 0; tt < tests; tt++) {
		scanf("%d", &n);
		for (int i = 0; i < n; i++) scanf("%lld", a + i);

		vector<int64> res;
		if (n == 1) {
			res.push_back(1); res.push_back(1);

			if (a[0] > 1)
				res.push_back(a[0] - 1);
		} else if (n == 2) {
			res.push_back(1); 
			res.push_back(a[1] + 1);
			if (a[0] > 1)
				res.push_back(a[0] - 1);
		} else {
			int index = 2 * ((n - 1) >> 1) - 1;
			
			for (int i = 0; i < index; i++) 
				res.push_back(a[i]);

			if (a[index] == 1) 
				res[res.size() - 1]++;
			else {
				res.push_back(a[index] - 1);
				res.push_back(1);
			} 
				
			int64 total = 0, unit = 0;
			for (int i = index + 1; i < n; i++) {
				if (i % 2 == 0) unit += a[i];
				total += a[i]; 
			}

			res.push_back(total - unit + 1);
			if (unit > 1)
				res.push_back(unit - 1);
		}

		printf("%d\n", res.size());
		for (int i = 0; i < res.size() - 1; i++)
			printf("%lld ", res[i]);
		printf("%lld\n", res[res.size() - 1]);
	}
}
