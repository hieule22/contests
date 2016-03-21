#include <iostream>

using namespace std;

class FenwichTree {
	int maxVal;
	int *tree;

public:
	FenwichTree(int num) : maxVal(num) {
		tree = new int[maxVal + 1];
		for (int i = 1; i <= maxVal; i++)
			tree[i] = 0;
	}

	~FenwichTree() {
		delete tree;
	}

	int read(int idx) {
		int sum = 0;
		while (idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx);
		}
		return sum + tree[0];
	}

	void update(int idx, int val) {
		if (idx == 0) {
			tree[0] += val;
			return;
		}
		while (idx <= maxVal) {
			tree[idx] += val;
			idx += (idx & -idx);
		}
	}
};

int main()
{
	int m, n, q;
	cin >> m >> n >> q;
	FenwichTree tree(n);
	int scenes[m];
	int actors[n];
	for (int i = 0; i < m; i++) 
		scenes[i] = 0;
	for (int i = 0; i < n; i++) {
		cin >> actors[i];
		scenes[actors[i]]++;
	}
	for (int i = 0; i < m; i++) {
		tree.update(scenes[i], 1);
	}

	int A, N, M, P;
	for (int i = 0; i < q; i++) {
		cin >> A;
		if (A == 1) {
			cin >> N >> M;
			tree.update(scenes[actors[N]], -1);
			scenes[actors[N]]--;
			tree.update(scenes[actors[N]], 1);
			
			tree.update(scenes[M], -1);
			scenes[M]++;
			tree.update(scenes[M], 1);

			actors[N] = M;
		} else {
			cin >> P;
			cout << tree.read(P - 1) << endl;
		}
	}
}

