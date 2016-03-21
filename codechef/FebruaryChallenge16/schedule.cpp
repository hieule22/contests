#include <iostream>

using namespace std;

void solve()
{
	int P, D, H, N;
	cin >> P >> D >> H >> N;
	
	int weekHours[P];
	for (int i = 0; i < P; i++)
		cin >> weekHours[i];
	
	int lunchBegin, lunchEnd;
	cin >> lunchBegin >> lunchEnd;

	int clients[D][H];
	for (int i = 0; i < D; i++)
		for (int j = 0; j < H; j++)
			cin >> clients[i][j];

	int isFree[P][D];
	for (int i = 0; )
}

int main()
{
	ios_base::sync_with_stdio(false);
	int t;
	cin >> t;
	for (int i = 0; i < t; i++)
		solve();

	return 0;
}