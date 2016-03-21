#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

class MySorter
{
public:
	bool operator() (const pair<int, int> lhs, const pair<int, int> rhs)
	{
		return lhs.second > rhs.second;
	}
} sorter;

int main()
{
	ios_base::sync_with_stdio(false);

	int t, n, m;
	cin >> t;

	for (int tt = 0; tt < t; tt++)
	{
		cin >> n >> m;

		unordered_map<int, vector<pair<int, int> > > row_num;
		unordered_map<int, vector<pair<int, int> > > col_num;

		int a[n][m], num;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cin >> a[i][j];
				num = a[i][j];
				if (row_num.find(num) == row_num.end()) {
					row_num[num].push_back(make_pair(i, 1));
				} else {
					auto & last = row_num[num].back();
					if (last.first == i) 
						last.second++;
					else
						row_num[num].push_back(make_pair(i, 1));
				}
			}
		}

		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				num = a[i][j];
				if (col_num.find(num) == col_num.end()) {
					col_num[num].push_back(make_pair(j, 1));
				} else {
					auto & last = col_num[num].back();
					if (last.first == j)
						last.second++;
					else
						col_num[num].push_back(make_pair(j, 1));
				}
			}
		}

		for (auto & entry : row_num) 
			sort(entry.second.begin(), entry.second.end(), sorter);
		for (auto & entry : col_num)
			sort(entry.second.begin(), entry.second.end(), sorter);

		int result = 0;
		for (auto & row : row_num) {
			int value = row.first;
			if (col_num.find(value) == col_num.end()) {
				result = max(result, row.second[0].second);
				continue;
			}

			int expected = row.second[0].second + col_num[value][0].second;
			bool overlap = true;
			for (auto & entry : row.second) {
				if (entry.second != row.second[0].second)
					break;
				for (auto & col : col_num[value]) {
					if (col.second != col_num[value][0].second)
						break;
					if (a[entry.first][col.first] != value) {
						overlap = false;
						break;
					}
				}
				if (!overlap)
					break;
			}
			if (overlap) result = max(result, expected - 1);
			else result = max(result, expected);
		}

		cout << result << endl;		
	}
}