#include <iostream>

using namespace std;

typedef signed long long int64;

int main()
{
	ios_base::sync_with_stdio(false);

	char ops[4] = {'/', '*', '+', '-'};

	int t, n;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		cin >> n;

		int64 res, num;
		cin >> res;

		int count = 0, index = 0;
		for (int i = 1; i < n; i++) {

			char cur_op = ops[index];
			count++;
			if (count == 4) {
				count = 0;
				index = (index + 2) % 4;
			} else {
				index = (index + 1) % 4;
			}

			cin >> num;
			
			if (cur_op == '+')
				res += num;
			else if (cur_op == '-')
				res -= num;
			else if (cur_op == '*')
				res *= num;
			else
				res /= num;
		}

		cout << res << endl;
	}
}