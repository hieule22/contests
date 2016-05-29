#include <iostream>
#include <algorithm>
#include <vector>
#include <cstdint>

using namespace std;
uint64_t a[50];

struct Triple
{
	uint64_t first, second, third;
	uint64_t perimeter;

	Triple(uint64_t f, uint64_t s, uint64_t t)
		: first(f), second(s), third(t)
	{
		perimeter = f + s + t;
	}
};

bool operator < (const Triple & lhs, const Triple & rhs)
{
	if (lhs.perimeter != rhs.perimeter)
		return lhs.perimeter < rhs.perimeter;
	if (lhs.third != rhs.third)
		return lhs.third < rhs.third;
	if (lhs.first != rhs.first)
		return lhs.first < rhs.first;
	return true;
}

bool check(const Triple & t)
{
	uint64_t a = t.first, b = t.second, c = t.third;
	if (a + b <= c)
		return false;
	if (a + c <= b)
		return false;
	if (b + c <= a)
		return false;
	return true;
}

int main()
{
	ios_base::sync_with_stdio(0);
	int n; cin >> n;
	for (int i = 0; i < n; i++)
		cin >> a[i];
	sort(a, a + n);

	Triple result(0, 0, 0);
	for (int i = 0; i < n; i++)
		for (int j = i + 1; j < n; j++)
			for (int k = j + 1; k < n; k++) {
				Triple triangle(a[i], a[j], a[k]);
				if (check(triangle))
					if (result < triangle)
						result = triangle;
			}


	if (result.perimeter == 0)
		cout << -1 << endl;
	else 
		cout << result.first << " " << result.second << " " << result.third << endl;
}