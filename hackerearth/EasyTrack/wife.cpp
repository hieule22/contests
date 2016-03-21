#include <iostream>
#include <unordered_set>
using namespace std;

int main()
{
	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++)
	{
		int n, x, price;
		cin >> n >> x;
		unordered_set<int> prices;
		for (int i = 0; i < n; i++) {
			cin >> price;
			prices.insert(price);
		}

		if (prices.size() == x) {
			cout << "Perfect husband" << endl;
		} else if (prices.size() > x) {
			cout << "Lame husband" << endl;
		} else {
			cout << "Bad husband" << endl;
		}
	}
}