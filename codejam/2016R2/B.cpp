#include <iostream>
#include <cstdio>
#include <algorithm>

using namespace std;
int N, K;
double prob[201], ans;

void process(int * data)
{
	double total = 0;
	for (unsigned int mask = 0; mask < (1 << K); mask++) {
		int cnt = __builtin_popcount(mask);
		if (cnt != K / 2) continue;
		double temp = 1;
		for (int i = 0; i < K; i++) {
			if (mask & (1 << i))
				temp *= prob[data[i]];
			else
				temp *= 1 - prob[data[i]];
		}
		total += temp;
	}
	ans = max(total, ans);
}

void permute(int * arr, int * data, int start, int end, int index, int r)
{
	if (index == r) {
		process(data);
		return;
	}

	for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
		data[index] = arr[i];
		permute(arr, data, i + 1, end, index + 1, r);
	}
}

void permute(int * arr, int n, int r)
{
	int data[r];
	permute(arr, data, 0, n - 1, 0, r);
}

void solve(int testNumber)
{
	cin >> N >> K;
	for (int i = 0; i < N; i++)
		cin >> prob[i];
	ans = 0;

	int taken[N];
	for (int i = 0; i < N; i++)
		taken[i] = i;

	permute(taken, N, K);
	printf("Case #%d: %.7f\n", testNumber, ans);
}

int main()
{
	ios_base::sync_with_stdio(0);
	int tests;
	cin >> tests;
	for (int tt = 1; tt <= tests; tt++)
		solve(tt);
}