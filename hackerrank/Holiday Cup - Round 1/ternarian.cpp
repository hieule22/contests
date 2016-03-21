#include <vector>
#include <iostream>
#define MAX_LEN 22

using namespace std;

int weights[MAX_LEN];

void convertToBase3(int x, int digits[]) {
	int index = 0, remainder;
	while (x) {
		remainder = x % 3;
		x = x / 3;
		digits[index++] = remainder;
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	weights[0] = 1;
	for (int i = 1; i < MAX_LEN; i++)
		weights[i] = 3 * weights[i - 1];	
	int n, x, digits[MAX_LEN], carry, pos, res;
	cin >> n;
	while (n--) {
		cin >> x;
		for (int i = 0; i < MAX_LEN; i++) digits[i] = 0;
		convertToBase3(x, digits);
		vector<int> leftPan;
		for (int i = 0; i < MAX_LEN; i++) {
			if (digits[i] > 1) {
				leftPan.push_back(weights[i]);
				digits[i] = 0;
				carry = 1, pos = i + 1;
				while (carry) {
					res = (digits[pos] + carry) % 3;
					carry = (res < digits[pos]) ? 1 : 0;
					digits[pos++] = res;
				}
			} 
		}
		cout << "left pan: ";
		vector<int>::reverse_iterator riter;
		for (riter = leftPan.rbegin(); riter != leftPan.rend(); riter++)
			cout << *riter << " ";
		cout << endl;
		cout << "right pan: ";
		for (int i = MAX_LEN - 1; i >= 0; i--) {
			if (digits[i]) cout << weights[i] << " ";
		}
		cout << endl << endl;
	}
}