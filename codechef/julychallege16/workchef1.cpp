#include <iostream>
#include <cstdint>
#include <cstring>
using namespace std;
 
int k;
uint64_t L, R;
int known[10];
 
bool Check(uint64_t n) {
	memset(known, 0, sizeof known);
	uint64_t temp = n;
	int cnt = 0;
	while (temp != 0) {
		int digit = temp % 10;
		if (digit != 0 && !known[digit] && n % digit == 0) {
			cnt++;
			known[digit] = 1;
		}
		temp /= 10;
	}
	return cnt >= k;
}
 
void Solve() {
	cin >> L >> R >> k;
	int cnt = 0;
	for (uint64_t i = L; i <= R; ++i)
		if (Check(i)) {
			cnt++;
			// cout << i << endl;
		}
	cout << cnt << endl;
}
 
int main() {
	int q;
	cin >> q;
	for (int i = 0; i < q; ++i)
		Solve();
} 