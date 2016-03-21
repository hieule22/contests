#include <iostream>
using namespace std;

class FenwichTree
{
private:
	int *tree;
	int max;
public:
	explicit FenwichTree(int maxValue) : max(maxValue) {
		tree = new int[max + 1];
		for (int i = 0; i < max + 1; i++)
			tree[i] = 0;
	}

	~FenwichTree() {
		delete [] tree;
	}

	int read(int index) const {
		int sum = 0;
		while (index > 0) {
			sum += tree[index];
			index -= (index & -index);
		}
		return sum;
	}

	void update(int index, int val) {
		while (index <= max) {
			tree[index] += val;
			index += (index & -index);
		}
	}
};

int main()
{
	int test;
	cin >> test;
	for (int tt = 0; tt < test; tt++)
	{
		int n;
		cin >> n;
		int sticker[n];
		for (int i = 0; i < n; i++)
			cin >> sticker[i];

		FenwichTree tree(n);
		bool chaotic = false;
		int res = 0;
		for (int i = n - 1; i >= 0; i--) {
			int inversions = tree.read(sticker[i] - 1);
			if (inversions > 2) {
				chaotic = true;
				break;
			}
			res += inversions;
			tree.update(sticker[i], 1);
		}

		if (chaotic)
			cout << "Too chaotic" << endl;
		else
			cout << res << endl;
	}
}