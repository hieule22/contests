#include <iostream>
#include <algorithm>

using namespace std;

int max_points[5] = {500, 1000, 1500, 2000, 2500};

int main() 
{
	ios_base::sync_with_stdio(false);
	int times[5], wrongs[5];
	int hs, hu;
	for (int i = 0; i < 5; i++)
		cin >> times[i];
	for (int i = 0; i < 5; i++)
		cin >> wrongs[i];
	cin >> hs >> hu;

	int total = 0;
	for (int i = 0; i < 5; i++) {
		total += max(3 * max_points[i] / 10, 
			max_points[i] - max_points[i] * times[i] / 250 - 50 * wrongs[i]);
	}
	total += (100 * hs - 50 * hu);

	cout << (int)total << endl;
}