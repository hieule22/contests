#include <stdio.h>
#define min(a, b) (a < b ? a : b)

typedef unsigned int uint;
const uint MAX_LENGTH = 1e5 + 5;
char s[MAX_LENGTH];
uint length;

int main()
{
	uint t;
	scanf("%d", &t);

	for (uint tt = 0; tt < t; tt++)
	{
		scanf("%d %s", &length, s);

		uint unred = 0, unblue = 0, ungreen = 0;
		for (uint i = 0; i < length; i++) {
			if (s[i] != 'R') unred++;
			if (s[i] != 'B') unblue++;
			if (s[i] != 'G') ungreen++;
		}

		uint result = min(unred, unblue);
		result = min(result, ungreen);

		printf("%d\n", result);
	}
}