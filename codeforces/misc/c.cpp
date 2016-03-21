#include <string.h>
#include <iostream>
#include <vector>
#include <string>

#define MAX 200005

const int N = 26;

using namespace std;
string st;
int cnt[N];
vector<int> odd;

int main() {
	ios_base::sync_with_stdio(0);

	cin >> st;
	int cnt[26] = {0};
	for (int i = 0; i < st.size(); i++) {
		cnt[st[i] - 'a']++;
	}

	for (int i = 0; i < 26; i++) {
		if (cnt[i] & 1) { 
			odd.push_back(i);
		}
	}

	int size = odd.size();
	int first = 0;
	int last = odd.size() - 1;
	while (first < last) {
		cnt[odd[first]]++;
		cnt[odd[last]]--;
		first++;
		last--;
	}

	

	int hasOdd = 0;
	for (int i = 0; i < 26; i++) {
		if (cnt[i] & 1) {
			hasOdd = 1;
			continue;
		}
		for (int j = 0; j < (cnt[i] >> 1); j++)
			cout << (char)('a' + i);
	}

	if (hasOdd) {
		for (int i = 0; i < cnt[last]; i++)
			cout << (char)('a' + last);
	}

	for (int i = 25; i >= 0; i--) {
		if (cnt[i] & 1) continue;
		for (int j = 0; j < (cnt[i] >> 1); j++)
			cout << (char)('a' + i);
	}

	cout << endl;
}