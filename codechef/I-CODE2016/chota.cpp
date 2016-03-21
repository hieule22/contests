#include <iostream>
#include <string>

using namespace std;
typedef unsigned long long uint64;


int getDigit(char n)
{
	return n - '0';
}

int main()
{
	ios_base::sync_with_stdio(false);

	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++)
	{
		string key;
		cin >> key;
		uint64 a, b;
		cin >> a >> b;

		if (key[0] == '0') {
			cout << "NO" << endl;
			continue;
		}

		uint64 left[key.length()];
		left[0] = getDigit(key[0]) % a;
		for (int i = 1; i < key.length(); i++)
			left[i] = (left[i - 1] * 10 + getDigit(key[i])) % a;

		uint64 right[key.length()];
		right[key.length() - 1] = getDigit(key[key.length() - 1]) % b;
		uint64 power = 1;
		for (int i = key.length() - 2; i >= 0; i--) {
			power = power * 10 % b;
			right[i] = (power * getDigit(key[i]) + right[i + 1]) % b;
		}

		bool yes = false;
		int i;
		for (i = 0; i < key.length() - 1; i++) {
			if (left[i] == 0 && right[i + 1] == 0 && key[i + 1] != '0') {
				yes = true;
				break;
			}
		}

		if (yes) {
			cout << "YES" << endl;
			for (int j = 0; j <= i; j++)
				cout << key[j];
			cout << endl;
			for (int j = i + 1; j < key.length(); j++)
				cout << key[j];
			cout << endl;
		} else {
			cout << "NO" << endl;
		}
	}

}
