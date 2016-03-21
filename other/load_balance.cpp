#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	int tasks[n];
	int total = 0;
	for (int i = 0; i < n; i++) {
		cin >> tasks[i];
		total += tasks[i];
	}
	sort(tasks, tasks + n);

	int loads[n];
	int share = total / n;
	int remain = total - share * n;
	int seconds = 0;
	for (int i = 0; i < remain; i++)
		seconds += abs(tasks[n - 1 - i] - share - 1);
	for (int i = remain; i < n; i++)
		seconds += abs(tasks[n - 1 - i] - share);
	
	cout << (seconds >> 1) << endl;

}