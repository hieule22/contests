#include <iostream>
#include <string>
#include <deque>
#define max(a, b) ((a > b) ? a : b)
using namespace std;

int q, k;
string a, b, c;
int aa[1000000], bb[1000000], cc[1000000];

string Trim(const string& a) {
	int i = 0;
	while (i < a.length() && a[i] == '0')
		i++;
	string res;
	if (i == a.length())
		return "0";
	else
		return res.append(a, i, string::npos);
}

char GetHexFromDec(int val) {
	if (val >= 0 && val <= 9) 
		return (char)('0' + val);
	return (char)('A' + (val - 10));
}

int GetDecFromHex(char n) {
	if (n >= '0' && n <= '9')
		return n - '0';
	return 10 + n - 'A';
}

int GetBit(const string& num, int pos) {
	int n = (pos >> 2);
	if (n >= num.size())
		return 0;
	int value = GetDecFromHex(num[num.size() - n - 1]);
	int offset = pos % 4;
	if (value & (1 << offset))
		return 1;
	else
		return 0;
}

int main() {
	cin >> q;
	for (int i = 0; i < q; i++) {
		cin >> k >> a >> b >> c;
		int length = max(a.size(), b.size());
		length = max(length, c.size());
		for (int i = 0; i < 4 * length; i++) {
			aa[i] = GetBit(a, i);
			bb[i] = GetBit(b, i);
			cc[i] = GetBit(c, i);

			if (cc[i] == 0 && (aa[i] | bb[i])) {
				if (aa[i] == 1) {
					aa[i] = 0;
					k--;
				}
				if (bb[i] == 1) {
					bb[i] = 0;
					k--;
				}
			} else if (cc[i] == 1 && !(aa[i] | bb[i])) {
				bb[i] = 1;
				k--;
			}
		}

		if (k < 0) {
			printf("-1\n");
			continue;
		}

		for (int i = 4 * length - 1; k > 0 && i >= 0; i--) {
			if (aa[i] == 1 && bb[i] == 1) {
				aa[i] = 0;
				k--;
			} else if (k > 1 && aa[i] == 1 && bb[i] == 0) {
				aa[i] = 0;
				bb[i] = 1;
				k -= 2;
			}
		}

		string ra, rb;
		for (int i = 4 * length - 1; i >= 0; i -= 4) {
			int val = aa[i] * 8 + aa[i - 1] * 4 + aa[i - 2] * 2 + aa[i - 3];
			ra.push_back(GetHexFromDec(val));
			val = bb[i] * 8 + bb[i - 1] * 4 + bb[i - 2] * 2 + bb[i - 3];
			rb.push_back(GetHexFromDec(val));
		}

		ra = Trim(ra);
		rb = Trim(rb);
		cout << ra << endl;
		cout << rb << endl;
	}	
}