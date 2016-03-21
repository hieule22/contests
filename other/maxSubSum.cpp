#include <vector>
#include <cstdarg>
#include <iostream>

using namespace std;

int max(int n, ...)
{
	int result = 0;
	va_list args;
	va_start(args, n);
	int num;
	for (int i = 0; i < n; i++)
	{
		num = va_arg(args, int);
		if (result < num)
			result = num;
	}
	va_end(args);
	return result;
}

/*
Recursive maximum subsequence sum algorithm.
Finds maximum sum in subarray spanning a[left..right]
Does not attempt to maintain actual best sequence
*/
int maxSumRec(const vector<int> &a, int left, int right)
{
	if (left == right)
		if (a[left] > 0)
			return a[left];
		else
			return 0;

	int center = left + ((right - left) >> 1);
	int maxLeftSum = maxSumRec(a, left, center);
	int maxRightSum = maxSumRec(a, center + 1, right);

	int maxLeftBorderSum = 0, leftBorderSum = 0;
	for (int i = center; i >= left; i--) 
	{
		leftBorderSum += a[i];
		if (leftBorderSum > maxLeftSum)
			maxLeftSum = leftBorderSum;
	}

	int maxRightBorderSum = 0, rightBorderSum = 0;
	for (int j = center + 1; j <= right; j++)
	{
		rightBorderSum += a[j];
		if (rightBorderSum > maxRightBorderSum)
			maxRightBorderSum = rightBorderSum;
	}

	return max(3, maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
}

int main()
{
	int arr[] = {4, -3, 5, -2, -1, 2, 6, -2};
	vector<int> a(arr, arr + 8);
	cout << maxSumRec(a, 0, a.size() - 1);
}