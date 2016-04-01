#include <stdio.h>
#include <algorithm>
#include <string.h>
#include <climits>

const int MAX_N = 1e5 + 5;
int n;
const char * HAPPY = "Happy";
char word[10];

int main()
{
	int t;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++)
	{
		scanf("%d %s", &n, word);
		unsigned long long total = 0;
		int minEven = INT_MAX, minOdd = INT_MAX, num;
		for (int i = 0; i < n; i++) {
			scanf("%d", &num);
			total += num;
			if (num & 1)
				minOdd = std::min(minOdd, num);
			else
				minEven = std::min(minEven, num);
		}
		int rem = (strcmp(word, HAPPY) == 0) ? 1 : 0;

		if (total % 2 == rem) {
			printf("%lld\n", total);
		} else if (total % 2 == 1) {
			if (minOdd == INT_MAX || total == minOdd)
				printf("Sad\n");
			else
				printf("%lld\n", total - minOdd);
		} else {
			if (minEven == INT_MAX || total == minEven) {
				printf("Sad\n");
			} else {
				printf("%lld\n", total - minEven);
			}
		}
	}
}