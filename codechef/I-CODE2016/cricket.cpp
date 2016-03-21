#include <unordered_map>
#include <string.h>
#include <stdio.h>

using namespace std;

int res;
char str[2005];
char type[27];

class Trie
{
public:
	bool terminal;
	Trie * child[26];

	Trie() : terminal(false) {
		for (int i = 0; i < 26; i++)
			child[i] = nullptr;
	}

	void insert(int left, int right)
	{
		if (!terminal) {
			terminal = true;
			res++;
		}

		if (left == right)
			return;

		if (child[str[left + 1] - 'a'] == nullptr)
			child[str[left + 1] - 'a'] = new Trie();
		child[str[left + 1] - 'a']->insert(left + 1, right);
	}
};

int main()
{
	int t, k;
	scanf("%d", &t);

	for (int tt = 0; tt < t; tt++)
	{
		scanf("%s %s %d", str, type, &k);
		int length = strlen(str);
		res = 0;
		Trie root;

		int left, right;
		for (left = 0; left < length; left++) {
			int cur = 0;
			for (right = left; right < length; right++) {
				if (type[str[right] - 'a'] == 'b')
					cur++;
				if (cur > k) break;
			}
			right--;
			if (left <= right) {
				if (root.child[str[left] - 'a'] == nullptr)
					root.child[str[left] - 'a'] = new Trie();
				root.child[str[left] - 'a']->insert(left, right);
			}
		}
		
		printf("%d\n", res);
	}
}
