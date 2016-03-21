#include <stdio.h>
#include <string.h>

const int MAX_LEN = 1000;
char a[MAX_LEN + 1], b[MAX_LEN + 1];
int fa[26];

int main()
{
	int t;
	scanf("%d", &t);

	for (int tt = 0; tt < t; tt++)
	{
		scanf("%s %s", a, b);
		memset(fa, 0, 26 * sizeof(int));
		for (int i = 0; a[i] != '\0'; i++)
			fa[a[i] - 'a'] = 1;
		bool ok = false;
		for (int i = 0; b[i] != '\0'; i++) {
			if (fa[b[i] - 'a'] == 1) {
				ok = true;
				break;
			}
		}

		if (ok)
			printf("Yes\n");
		else
			printf("No\n");
	}
}