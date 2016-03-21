#include <stdio.h>
#include <algorithm>
#define abs(a) (a >= 0 ? a : -a)

using namespace std;
typedef long long int64;


const int MAX_LEN = 100005;
int a[MAX_LEN], b[MAX_LEN];
int n, k;

int main()
{
	int t;
	scanf("%d", &t);

	for (int tt = 0; tt < t; tt++)
	{
		scanf("%d %d", &n, &k);
		for (int i = 0; i < n; i++)
			scanf("%d", a + i);
		for (int i = 0; i < n; i++)
			scanf("%d", b + i);

		int64 sum = 0;
		for (int i = 0; i < n; i++)
			sum += (int64)a[i] * b[i];

		sort(b, b + n);
		int delta = max(abs(b[0]), abs(b[n - 1]));
		sum += (int64)delta * k;

		printf("%lld\n", sum);
	}
}