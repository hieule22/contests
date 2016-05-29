#include <iostream>

using namespace std;
int n, r, p, s;
string ans;

char getLoser(char c)
{
	if (c == 'R')
		return 'S';
	else if (c == 'S')
		return 'P';
	else
		return 'R';
}

void sort(string &word, int left, int right)
{
	int size = right - left;
	if (word.substr(left, size) > word.substr(right, size)) {
		for (int i = left; i < right; i++)
			swap(word[i], word[i + size]);
	}
}

void improve(string &word)
{
	for (int i = 0; i < n; i++) {
		int gap = (1 << (i + 1));
		int small = (1 << i);
		for (int j = 0; j < word.size(); j += gap) {
			sort(word, j, j + small);
		}
	}
}

void check(char winner)
{
	string word;
	word.push_back(winner);
	for (int i = 0; i < n; i++) {
		string frontier;
		for (char a : word) {
			char b = getLoser(a);
			frontier.push_back(a);
			frontier.push_back(b);
		}
		word = frontier;
	}

	int rCount = 0, pCount = 0, sCount = 0;
	for (char c : word) {
		if (c == 'R') rCount++;
		else if (c == 'P') pCount++;
		else sCount++;
	}

	if (rCount == r && pCount == p && sCount == s) {
		improve(word);
		if (ans.size() == 0 || word < ans)
			ans = word;
	}
}

void solve(int testNumber)
{
	cin >> n >> r >> p >> s;
	ans.clear();
	check('R');
	check('P');
	check('S');
	if (ans.size() == 0)
		cout << "Case #" << testNumber << ": IMPOSSIBLE" << endl;
	else
		cout << "Case #" << testNumber << ": " << ans << endl;
}

int main()
{
	ios_base::sync_with_stdio(0);
	int tests;
	cin >> tests;
	for (int tt = 1; tt <= tests; tt++)
		solve(tt);
}