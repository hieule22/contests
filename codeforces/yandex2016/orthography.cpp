#include <cstdio>
#include <cstring>

char dict[1001][41];
bool valid[1001];
int length;

int dist(int a, int b)
{
	int ans = 0;
	for (int i = 0; i < length; i++)
		if (dict[a][i] != dict[b][i]) {
			ans++;
			if (ans > 1)
				return ans;
		}
	return ans;
}

int main()
{
	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		scanf("%s", dict[i]);
		valid[i] = true;
	}

	length = strlen(dict[0]);
	for (int i = 0; i < n; i++) {
		if (!valid[i]) continue;
		bool ok = true;
		for (int j = 0; j < n; j++) {
			if (dist(i, j) > 1) {
				ok = false;
				valid[i] = valid[j] = false;
				break;
			}
		}
		if (ok) {
			printf("%s\n", dict[i]);
			return 0;
		}
	}
}