#include <string>
#include <iostream>

using namespace std;

inline bool isSeparator(char c) {
	return c == ',' || c == ';';
}

inline bool isNumeric(char c) {
	return c >= '0' && c <= '9';
}

inline bool isInteger(string word) {
	if (word.size() == 0) return false;
	for (int i = 0; i < word.size(); i++)
		if (!isNumeric(word[i])) 
			return false;
	if (word.size() == 1) return true;
	return word[0] != '0';
}

int main() {
	ios_base::sync_with_stdio(false);
	string input, a, b, word;
	cin >> input;
	char cur;
	int cnt = 0;
	for (int i = 0; i < input.size(); i++) {
		cur = input[i];
		if (!isSeparator(cur)) {
			word += cur;
		} else {
			if (isInteger(word)) {
				if (a.size() > 0) a += ",";
				a += word;
			} else {
				if (cnt > 0) b += ",";
				b += word;
				cnt++;
			}
			word.clear();
		}
	}

	if (isInteger(word)) {
		if (a.size() > 0) a += ",";
		a += word;
	} else {
		if (cnt > 0) b += ",";
		b += word;
		cnt++;
	}

	cout << (a.size() > 0 ? ("\"" + a + "\"") : "-") << endl;
	cout << (cnt > 0 ? ("\"" + b + "\"") : "-") << endl;
}