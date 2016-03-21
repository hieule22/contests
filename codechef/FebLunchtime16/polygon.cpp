#include <iostream>
#include <algorithm>

using namespace std;

const int MaxN = 3e3 + 10;
const int MaxC = 1e4 + 10;

struct Point
{
	int x, y;
};

int cw(const Point &a, const Point &b, const Point &c) {
	return (a.x - b.x) * (c.y - b.y) - (a.y - b.y) * (c.x - b.x);
}

bool inTriangle(const Point &a, const Point &b, const Point &c, const Point &d)
{
	int bcd = abs(cw(b, c, d));
	int acd = abs(cw(a, c, d));
	int abd = abs(cw(a, b, d));
	int abc = abs(cw(a, b, c));
	return abc == abd + acd + bcd;
}

Point white[MaxN], black[MaxN], origin;
int cnt[MaxN][MaxN], ord[MaxN], n, m, q, k;

int main() 
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> m;
	for (int i = 0; i < n; ++i) {
		cin >> white[i].x >> white[i].y;
		white[i].x += MaxC;
		white[i].y += MaxC;
	}
	for (int i = 0; i < m; ++i) {
		cin >> black[i].x >> black[i].y;
		black[i].x += MaxC;
		black[i].y += MaxC;
	}
	for (int i = 0; i < n; ++i) {
		for (int j = 0; j < n; ++j) {
			if (cw(white[i], origin, white[j]) > 0) {
				for (int k = 0; k < m; ++k) {
					if (inTriangle(white[i], origin, white[j], black[k]))
						cnt[i][j]++;
				}
			}
			cnt[j][i] = -cnt[i][j];
		}
	}

	cin >> q;
	for (int qi = 0; qi < q; ++qi) {
		cin >> k;
		for (int i = 0; i < k; ++i) {
			cin >> ord[i];
			ord[i]--;
		}
		int ans = 0;
		for (int i = 0; i < k; ++i) {
			int ni = (i == k - 1) ? 0 : i + 1;
			ans += cnt[ord[i]][ord[ni]];
		}
		cout << abs(ans) << endl;
	}
	return 0;
}