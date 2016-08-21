#include <iostream>

using namespace std;

void Solve() {
	int n;
	cin >> n;
	int total = 0, score;

	bool necessary = true;
	bool sufficient = false;
	for (int i = 0; i < n; i++) {
		cin >> score;
		if (score == 2) necessary = false;
		if (score == 5) sufficient = true;
		total += score;
	}

	if (!necessary || !sufficient) {
		cout << "No" << endl;
		return;
	}

	double gpa = 1.0 * total / n;
	if (gpa >= 4.0)
		cout << "Yes" << endl;
	else
		cout << "No" << endl;
}

int main() {
	int t;
	cin >> t;
	for (int i = 0; i < t; ++i)
		Solve();
}