#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

typedef unsigned long long uint64;

class GadgetComparator
{
public:
	bool operator() (const pair<int, uint64> &g1, const pair<int, int> &g2) const
	{
		return g1.second < g2.second;
	}
} gadget_cmp;

int main()
{
	ios_base::sync_with_stdio(false);
	int n, m, k; 
	uint64 s;
	cin >> n >> m >> k >> s;
	
	uint64 dRate[n + 1], pRate[n + 1];
	for (int i = 1; i <= n; i++)
		cin >> dRate[i];
	for (int i = 1; i <= n; i++)
		cin >> pRate[i];

	vector<pair<int, uint64> > dGadget, pGadget;
	int type;
	uint64 cost;
	for (int i = 1; i <= m; i++) {
		cin >> type >> cost;
		if (type == 1)
			dGadget.push_back(make_pair(i, cost));
		else
			pGadget.push_back(make_pair(i, cost));
	} 

	sort(dGadget.begin() + 1, dGadget.end(), gadget_cmp);
	sort(pGadget.begin() + 1, pGadget.end(), gadget_cmp);


	uint64 pCum[pGadget.size()], dCum[dGadget.size()];
	pCum[0] = pGadget[0].second;
	for (int j = 1; j < pGadget.size(); j++)
		pCum[j] = pCum[j - 1] + pGadget[j].second;
	dCum[0] = dGadget[0].second;
	for (int j = 1; j < dGadget.size(); j++)
		dCum[j] = dCum[j - 1] + dGadget[j].second;

	int minDRate = INT_MAX, minPRate = INT_MAX;
	int pDay, dDay;

	bool isOk = false;
	for (int i = 1; !isOk && i <= n; i++) 
	{
		if (dRate[i] < minDRate) {
			minDRate = dRate[i];
			dDay = i;
		}
		if (pRate[i] < minPRate) {
			minPRate = pRate[i];
			pDay = i;
		}

		int low = max(0, k - (int)pGadget.size());
		int high = min(k, (int)dGadget.size());
		for (int dollar = low; dollar <= high; dollar++) {
			int pound = k - dollar;
			uint64 total = 0;
			if (dollar > 0)
				total += dCum[dollar - 1] * minDRate;
			if (pound > 0)
				total += pCum[pound - 1] * minPRate;
			if (total <= s) {
				isOk = true;
				cout << i << "\n";
				for (int j = 0; j < dollar; j++)
					cout << dGadget[j].first << " " << dDay << "\n";
				for (int j = 0; j < pound; j++)
					cout << pGadget[j].first << " " << pDay << "\n";
				break;
			}
		}
	}

	if (!isOk)
		cout << "-1 \n";
}










