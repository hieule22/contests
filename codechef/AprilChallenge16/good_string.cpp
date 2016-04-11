#include <stdio.h>
#include <iostream>
#include <string.h>
#include <string>
#include <unordered_set>
#include <vector>

using namespace std;
typedef unsigned int uint;
const char ALPHABET[3] = {'a', 'b', 'c'};
vector<vector<string> > dict;
uint a, n, k, cnt;
char s[51], temp[51];

bool verify(uint index, const char * str)
{
	uint mid = ((index + 1) >> 1);
	uint low = ((index & 1) ? 1 : 0);

	for (; mid < index; mid++, low += 2)
		if (str[low] == str[mid] && str[mid] == str[index])
			return false;
	return true;
}

void backtrack(char * str, uint length, uint index, vector<string> & str_set)
{
	if (index == length)
	{
		str_set.push_back(string(str));
		return;
	}

	for (uint i = 0; i < 3; i++)
	{
		str[index] = ALPHABET[i];
		if (verify(index, str))
		{
			backtrack(str, length, index + 1, str_set);
		}
	}
}

uint hamming(const char * left, const string & right)
{
	uint res = 0;
	for (uint i = 0; i < n; i++)
		if (left[i] != right[i])
			res++;
	return res;
}

void count(uint index, uint dist)
{
	if (dist > k)
		return;
	if (index == n) {
		cnt++;
		return;
	}

	for (uint i = 0; i < a; i++)
	{
		temp[index] = ALPHABET[i];
		if (verify(index, temp))
		{
			if (temp[index] != s[index])
				count(index + 1, dist + 1);
			else
				count(index + 1, dist);
		}
	}
}

int main()
{
	for (uint i = 1; i <= 26; i++)
	{
		char str[i + 1];
		str[i] = '\0';
		vector<string> str_set;
		backtrack(str, i, 0, str_set);
		dict.push_back(str_set);
	}

	uint tests;
	scanf("%d", &tests);
	while (tests--)
	{
		scanf("%d %d %s", &a, &k, s);
		n = strlen(s);
		cnt = 0;
		if (a == 3 && n <= 26)
		{	
			for (auto & str : dict[n - 1])
			{
				if (hamming(s, str) <= k)
					cnt++;
			}
		} else {
			if (n <= 8) count(0, 0); 
		}
		printf("%d\n", cnt);
	}

}