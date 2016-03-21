#include <iostream>
#include <algorithm>
#include <string>

const int MAX = (int)1e5 + 5;
std::string s1, s2;

int main()
{
	int t;
	std::cin >> t;

	for (int tt = 0; tt < t; tt++)
	{
		std::cin >> s1 >> s2;
		int length = s1.length();
		int f[2][26];
		for (int i = 0; i < 26; i++)
			f[0][i] = f[1][i] = 0;

		int n = 0;
		for (int i = 0; i < length; i++)
		{
			if (s1[i] == s2[i])
				n++;
			else {
				f[0][s1[i] - 'a']++;
				f[1][s2[i] - 'a']++;
			}
		}

		int j = 0;
		for (int i = 0; i < 26; i++)
		{
			j += std::min(f[0][i], f[1][i]);
		}

		printf("%dN%dJ%dS\n", n, j, length - n - j);
	}
}