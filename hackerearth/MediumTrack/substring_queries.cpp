#include <iostream>
#include <string>
using namespace std;

string s, qstr;
int mask_cnt;

int locate(int index) {
	for (int i = 0; i < qstr.length(); i++) {
		if (qstr[i] == s[index])
			return i;
	}
	return -1;
}

int count(int begin, int end) {
	if (begin == end) {
		if (qstr.length() == 1 && s[begin] == qstr[0])
			return 1;
		else
			return 0;
	}
	int mid = (begin + end) / 2;
	int total = count(begin, mid) + count(mid + 1, end);
	
	int left[mask_cnt], right[mask_cnt];
	left[0] = mid - begin + 1;
	for (int i = 1; i < mask_cnt; i++) 
		left[i] = 0;

	for (int i = mid; i >= begin; i--) {
		int pos = locate(i);
		if (pos >= 0) {
			for (int mask = 0; mask < mask_cnt; mask++) {
				if (!(mask & (1 << pos)) && left[mask] && !left[mask + (1 << pos)]) 
					left[mask + (1 << pos)] = i - begin + 1;
			}
		}
	}

	right[0] = end - mid;
	for (int i = 1; i < mask_cnt; i++)
		right[i] = 0;

	for (int i = mid + 1; i <= end; i++) {
		int pos = locate(i);
		if (pos >= 0) {
			for (int mask = 0; mask < mask_cnt; mask++) {
				if (!(mask & (1 << pos)) && right[mask] && !right[mask + (1 << pos)])
					right[mask + (1 << pos)] = end - i + 1;
			}
		}
	}

	for (int i = 0; i < mask_cnt; i++)
		total += left[i] * right[mask_cnt - 1 - i];

	return total;
}

void solve()
{
	
	int q;
	cin >> s >> q;
	for (int i = 0; i < q; i++) 
	{	
		cin >> qstr;
		mask_cnt = (1 << qstr.length());
		cout << count(0, s.length() - 1) << endl;
	}
}

int main()
{
	ios_base::sync_with_stdio(0);
	int t;
	cin >> t;
	for (int i = 0; i < t; i++)
		solve();
}