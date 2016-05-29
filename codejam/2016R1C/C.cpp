#include <iostream>
#include <vector>

using namespace std;
int J, P, S, K;
int taken[101][101][101];

template <class T, class U, class V>
class Triple {
public:
	T first; 
	U second; 
	V third;

	Triple(T f, U s, V t) : first(f), second(s), third(t) {}
};

template<class T, class U, class V>
ostream & operator<< (ostream & str, const Triple<T, U, V> & triple)
{
	str << triple.first << " " << triple.second << " " << triple.third;
	return str;
}

void solve(int test)
{
	cin >> J >> P >> S >> K;
	for (int i = 0; i <= J; i++)
		for (int j = 0; j <= P; j++)
			for (int k = 0; k <= S; k++)
				taken[i][j][k] = 0;

	vector<Triple<int, int, int> > outfits;
	for (int j = 1; j <= J; j++) {
		for (int p = 1; p <= P; p++) {
			for (int s = 1; s <= S; s++) {
				if (taken[j][p][s]) continue;
				if (taken[j][p][0] == K || taken[j][0][s] == K || taken[0][p][s] == K)
					continue;
				outfits.push_back(Triple<int, int, int>(j, p, s));
				taken[j][p][s]++;
				taken[j][p][0]++;
				taken[j][0][s]++;
				taken[0][p][s]++;
			}
		}
	}

	cout << "Case " << "#" << test << ": " << outfits.size() << endl;
	for (auto & t : outfits)
		cout << t << endl;
}

int main()
{
	ios_base::sync_with_stdio(0);
	int test; cin >> test;
	for (int t = 1; t <= test; t++)
		solve(t);
	return 0;
}