#include <stdio.h>
 
// typedef unsigned long long uint64;
 
const int LEN = 10;
const int MAX = (1 << LEN);
const int MOD = 1000000007;
 
char str[LEN + 1];
int last[MAX];
int update[MAX];
 
int main() {
	int t;
	scanf("%d", &t);

	for (int i = 0; i < t; i++) {
		int n;
		scanf("%s %d", str, &n);
	 
		int src = 0, temp = 1;
		for (int i = 0; i < LEN; i++) {
			if (str[i] == 'w') src |= temp;
			temp <<= 1; 
		}
	 
		for (int i = 0; i < MAX; i++) { 
			last[i] = update[i] = 0;
		}
	 
		last[0] = update[0] = 1;
	 
		int mask, xOr;
		for (int i = 0; i < n; i++) {
			scanf("%s", str);
			mask = 0;
			temp = 1;
			for (int j = 0; j < LEN; j++) {
				if (str[j] == '+') mask |= temp;
				temp <<= 1;
			}
			for (int j = 0; j < MAX; j++) {
				if (last[j]) {
					xOr = mask ^ j;
					update[xOr] = last[xOr] + last[j];
					if (update[xOr] >= MOD) update[xOr] %= MOD;
				}
			}
			for (int j = 0; j < MAX; j++)
				last[j] = update[j];
		}
	 
		printf("%d\n", last[src]);
	}
}
 