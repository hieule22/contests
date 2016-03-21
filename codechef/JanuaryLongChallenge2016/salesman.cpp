#include <stdio.h>
#include <vector>
using namespace std;
typedef long long int64;

const int64 MOD = 1000 * 1000 * 1000 + 7;
const int MAX = 100005;

int n, m;
int64 fact[MAX];

int64 power(int a, int b)
{
	if (b == 0)
		return 1;
	int64 res = power(a, b >> 1);
	res = (res * res) % MOD;
	if (b & 1)
		res = (res * a) % MOD;
	return res;
}

int main()
{
	fact[0] = 1;
	for (int i = 1; i < MAX; i++)
		fact[i] = (fact[i - 1] * i) % MOD;

	int t;
	scanf("%d", &t);
	for (int tt = 0; tt < t; tt++) 
	{
		scanf("%d %d", &n, &m);
		vector<pair<int, int> > edges(m);
		int x, y;
		for (int i = 0; i < m; i++) {
			scanf("%d %d", &x, &y);
			edges[i] = make_pair(x, y);
		}

		int64 total = 0, offset;

		for (int mask = 1; mask < (1 << m); mask++) {
			vector<vector<int> > clusters;
			bool valid = true;

			int bits = 0;
			for (int i = 0; i < m; i++) {
				if (mask & (1 << i)) {
					// printf("Mask\n");
					bits++;
					bool include = false;
					for (auto cluster : clusters) {
						for (int j = 1; j < cluster.size() - 1; j++) {
							if (cluster[j] == edges[i].first || cluster[j] == edges[i].second) {
								valid = false;
							}
						}

						if (cluster.front() == edges[i].second) {
							include = true;
							cluster.insert(cluster.begin(), edges[i].first);
						} else if (cluster.front() == edges[i].first) {
							include = true;
							cluster.insert(cluster.begin(), edges[i].second);
						} else if (cluster.back() == edges[i].first) {
							include = true;
							cluster.push_back(edges[i].second);
						} else if (cluster.back() == edges[i].second) {
							include = true;
							cluster.push_back(edges[i].first);
						}
					}

					if (!include) {
						// printf("Here\n");
						vector<int> cluster;
						cluster.push_back(edges[i].first);
						cluster.push_back(edges[i].second);
						clusters.push_back(cluster);
					}
				}
			}

			if (!valid) continue;

			int cnt = n;
			for (auto cluster : clusters) 
				cnt -= cluster.size();
			cnt += clusters.size();

			// printf("%d %d\n", clusters.size(), cnt);
			int64 offset = fact[cnt];
			offset = (offset * power(2, clusters.size())) % MOD;

			if (bits & 1) {
				total = (total + offset) % MOD;
			} else {
				total -= offset;
				if (total < 0) 
					total += MOD;
			}
		}

		int64 res = fact[n] - total;
		if (res < 0)
			res += MOD;
		printf("%lld\n", res);
	}
}