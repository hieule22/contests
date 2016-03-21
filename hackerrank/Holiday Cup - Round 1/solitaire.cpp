#include <iostream>
#include <stack>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	stack<int> cards;
	int cur;
	for (int i = 0; i < n; i++) {
		cin >> cur;
		if (!cards.empty() && ((cards.top() + cur) & 1) == 0)
			cards.pop();
		else
			cards.push(cur);
	}
	cout << cards.size() << endl;
}