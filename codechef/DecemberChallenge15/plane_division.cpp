#include <iostream>
#include <unordered_map>
#include <unordered_set>
#define max(a, b) ((a > b) ? a : b)

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	while (t--) {
		int n;
		double a, b, c;
		unordered_map<double, int> para;
		unordered_map<double, unordered_set<double> > unique;
		unordered_set<double> vertical;
		cin >> n;
		while (n--) {
			cin >> a >> b >> c;
			if (b == 0) {
				vertical.insert(-c / a);
			} else {
				double gradient = -a / b;
				double intersect = -c / b;
				if (para.find(gradient) == para.end() || 
					unique[gradient].find(intersect) == unique[gradient].end()) {
					para[gradient]++;
					unique[gradient].insert(intersect);
				}
			}
		}
		int res = 0;
		res = max(res, vertical.size());
		for (auto iter = para.begin(); iter != para.end(); iter++)
			res = max(res, iter->second);
		cout << res << endl;
	}
}