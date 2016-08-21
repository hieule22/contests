#include <iostream>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n, s;
	cin >> n >> s;
	int floor[s + 1];
	for (int i = 0; i < s + 1; i++)
		floor[i] = 0;
	int curFloor, time;
	for (int i = 0; i < n; i++) {
		cin >> curFloor >> time;
		if (floor[curFloor] < time)
			floor[curFloor] = time;
	}

	int total = 0;
	for (int i = s; i >= 0; i--) {
		if (total < floor[i])
			total = floor[i];
		total++;
	}

	cout << total - 1 << endl;
}