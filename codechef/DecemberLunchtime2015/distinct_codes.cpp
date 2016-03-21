#include <iostream>
#include <unordered_set>
#include <string>
using namespace std;

int main()
{
	int t;
	cin >> t;
	string str, code;
	for (int tt = 0; tt < t; tt++) {
		unordered_set<string> codes;
		cin >> str;
		for (int i = 0; i < str.length() - 1; i++) {
			code = str[i] + str[i + 1];
			cout << code << endl;
			codes.insert(code);
		}
		cout << codes.size() << endl;
	}
}