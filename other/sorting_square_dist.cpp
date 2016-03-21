#include <iostream>

using namespace std;

typedef unsigned long long uint64;

uint64 _mergeSort(int arr[], int temp[], int left, int right);
uint64 merge(int arr[], int temp[], int left, int mid, int right);

uint64 countInversions(int arr[], int n)
{
	int *temp = (int*)malloc(sizeof(int) * n);
	return _mergeSort(arr, temp, 0, n - 1);
}

uint64 _mergeSort(int arr[], int temp[], int left, int right)
{
	int mid; 
	uint64 inv_count = 0;
	if (right > left)
	{
		mid = (right + left) / 2;
		inv_count = _mergeSort(arr, temp, left, mid);
		inv_count += _mergeSort(arr, temp, mid + 1, right);

		inv_count += merge(arr, temp, left, mid + 1, right);
	}
	return inv_count;
}

uint64 merge(int arr[], int temp[], int left, int mid, int right)
{
	int i, j, k;
	uint64 inv_count = 0;

	i = left;
	j = mid;
	k = left;
	while ((i <= mid - 1) && (j <= right)) 
	{
		if (arr[i] <= arr[j])
		{
			temp[k++] = arr[i++];
		}
		else
		{
			temp[k++] = arr[j++];
			inv_count = inv_count + (mid - i);
		}
	}

	while (i <= mid - 1)
		temp[k++] = arr[i++];

	while (j <= right)
		temp[k++] = arr[j++];

	for (i = left; i <= right; i++)
		arr[i] = temp[i];

	return inv_count;
}

int main()
{
	ios_base::sync_with_stdio(false);
	int n;
	cin >> n;
	int id[n];
	for (int i = 0; i < n; i++)
		cin >> id[i];
	cout << countInversions(id, n) << endl;
}