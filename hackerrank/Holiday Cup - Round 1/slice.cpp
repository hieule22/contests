#include <stdio.h>
#include <iostream>
#include <cstring>
#define PI 3.14159265358979323846

using namespace std;

const int TOTAL = 360 * 60 * 60;

int main() {
	ios_base::sync_with_stdio(false);	
	int m;
	cin >> m;
	int modulo[TOTAL];
	while (m--) {
		int r, n, theta, minute, second;
		cin >> r >> n >> theta >> minute >> second;
		int delta = theta * 60 * 60 + minute * 60 + second;
		memset(modulo, 0, sizeof(modulo));
		int cur = 0;
		for (int i = 0; i < n; i++) {
			if (modulo[cur]) break;
			modulo[cur] = 1;
			cur = (cur + delta) % TOTAL;
		}
		int max = 0;
		int last = 0;
		for (int i = 0; i < TOTAL; i++) {
			if (modulo[i]) {
				if (i - last > max) {
					max = i - last;
				}
				last = i;
			}
		} 

		max = (max < TOTAL - last) ? TOTAL - last : max;
		double area = PI * r * r * max / TOTAL;
		printf("%.6f\n", area);
	}
}