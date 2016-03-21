#include <algorithm>
#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

typedef unsigned long long uint64;

int main()
{
	int q, t;
	uint64 n, k, temp;
	cin >> n >> q;

	vector<pair<int, int> > divisors;
	int limit = (int)sqrt(n);
	temp = n;
	int numFactors = 1;
	for (int i = 2; i <= limit; i++) {
		if (temp % i == 0) {
			pair<int, int> p = make_pair(i, 0);
			while (temp % i == 0) {
				temp /= i;
				p.second++;
			}
			divisors.push_back(p);
			numFactors *= (p.second + 1);
			if (temp == 1)
				break;
		}
	}

	if (temp != 1) {
		divisors.push_back(make_pair(n, 1));
		numFactors *= 2;
	}

	int res, cnt;
	for (int ii = 0; ii < q; ii++) 
	{
		cin >> t >> k;
		if (t == 1) {
			res = 1;
			for (auto iter = divisors.begin(); iter != divisors.end(); iter++) {
				cnt = 0;
				while (k % iter->first == 0) {
					cnt++;
					k /= iter->first;
				}
				cnt = min(cnt, iter->second);
				res *= (cnt + 1);
			}
		} else if (t == 2) {
			if (n % k != 0) {
				res = 0;
			} else {
				uint64 factor = n / k;
				res = 1;
				for (auto iter = divisors.begin(); iter != divisors.end(); iter++) {
					cnt = 0;
					while (factor % iter->first == 0) {
						cnt++;
						factor /= iter->first;
					}
					res *= (cnt + 1);
				}
			}
		} else {
			res = 0;
			for (auto iter = divisors.begin(); iter != divisors.end(); iter++) {
				cnt = 0;
				while (k % iter->first == 0) {
					cnt++;
					k /= iter->first;
				}
				// cout << iter->first << " " << cnt << endl;
				cnt = iter->second - cnt;
				cnt = max(0, cnt);
				// cout << cnt << endl; 
				res += cnt * numFactors / (iter->second + 1);
			}
			res--;
			if (k != 1) res = numFactors;
		}
		cout << res << endl;
	}
}