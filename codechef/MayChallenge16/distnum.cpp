#include <iostream>
#include <algorithm>
#include <queue>
#include <unordered_set>
#include <vector>
#define MAX_N 100005
#define INF 1000000001
 
int arr[MAX_N];
int n, q;
int a, b, c, d, k;
int result, left, right;
int min_tree[MAX_N * 3];
 
template <typename T>
struct ReverseComparator
{
	bool operator() (const T & lhs, const T & rhs)
	{
		return lhs > rhs;
	}
};
 
void init(int index, int low, int high)
{
	if (low == high) {
		min_tree[index] = arr[low];
		return;
	}
 
	int mid = low + ((high - low) >> 1);
	init(2 * index + 1, low, mid);
	init(2 * index + 2, mid + 1, high);
 
	min_tree[index] = std::min(min_tree[2 * index + 1], min_tree[2 * index + 2]);
}
 
int find_min(int index, int low, int high)
{
	if (low > right || high < left)
		return INF;
	if (low >= left && high <= right)
		return min_tree[index];
 
	int mid = low + ((high - low) >> 1);
	return std::min(find_min(2 * index + 1, low, mid), find_min(2 * index + 2, mid + 1, high));
}
 
int main()
{
	std::ios_base::sync_with_stdio(0);
	std::cin >> n >> q;
	for (int i = 0; i < n; i++) {
		std::cin >> arr[i];
	}
 
	init(0, 0, n - 1);
 
	result = 0;
	for (int qq = 0; qq < q; qq++) {
		std::cin >> a >> b >> c >> d >> k;
		left = (a * std::max(result, 0) + b) % n;
		right = (c * std::max(result, 0) + d) % n;
		if (left > right)
			std::swap(left, right);
 
		if (k > 1) {
			std::priority_queue<int, std::vector<int>, ReverseComparator<int> > heap;
			std::unordered_set<int> seen;
			for (int i = left; i <= right; i++) {
				if (seen.find(arr[i]) == seen.end()) {
					seen.insert(arr[i]);
					heap.push(arr[i]);
				}
			}
 
			if (heap.size() < k)
				result = -1;
			else {
				for (int i = 0; i < k - 1; i++)
					heap.pop();
				result = heap.top();
			}
 
		} else {
			result = find_min(0, 0, n - 1);
		}
 
		std::cout << result << std::endl;
	}
} 