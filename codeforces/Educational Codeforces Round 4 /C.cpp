#include <iostream>
#include <vector>
#include <string.h>
using namespace std;

char open_bracket[] = {'<', '{', '[', '('};
char close_bracket[] = {'>', '}', ']', ')'};

int find(char *set, char c) {
	for (int i = 0; i < 4; i++) {
		if (set[i] == c)
			return i + 1;
	}
	return 0;
}

int main()
{
	vector<char> stack;
	string s;
	cin >> s;
	bool isOk = true;
	int cnt = 0;
	for (int i = 0; i < s.length(); i++) {
		if (find(open_bracket, s[i])) {
			stack.push_back(s[i]);
		} else {
			if (stack.empty() || !find(open_bracket, stack.back())) {
				isOk = false;
				break;
			} 
			if (find(open_bracket, stack.back()) != find(close_bracket, s[i])) 
				cnt++;
			stack.pop_back(); 
		}
	}

	if (isOk)
		isOk = stack.empty();

	if (isOk)
		cout << cnt << endl;
	else
		cout << "Impossible" << endl;
}