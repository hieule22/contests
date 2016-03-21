#include <stdio.h>
#include <string.h>
 
const int MAX_LEN = 1e6 + 5;
char s[MAX_LEN];
int range[MAX_LEN];
int n;
 
int main()
{
	int d;
	scanf("%d", &d);
	for (int tt = 0; tt < d; tt++)
	{
		scanf("%s", s);
		n = strlen(s);
		bool isSpecial = true;
		if (n & 1) {
			int dist = n / 2;
			memset(range, 0, n * sizeof(int));
			for (int i = 0; i < n - dist; i++) {
				if (s[i] != s[i + dist]) {
					range[0]--;
					range[i]++;
					range[i + dist + 1]--;
				}
				if (i + dist + 1 < n && s[i] != s[i + dist + 1]) {
					range[i + 1]--;
					range[i + dist + 1]++;
				}
			}
			isSpecial = (range[0] == 0);
			for (int i = 1; i < n && !isSpecial; i++) {
				range[i] += range[i - 1];
				if (range[i] == 0)
					isSpecial = true; 
			}
		} else {
			int first = 0, second = n / 2;
			for (; second < n; first++, second++) {
				if (s[first] != s[second]) {
					isSpecial = false;
					break;
				}
			}
		}
		printf((isSpecial ? "YES\n" : "NO\n"));
	}
} 