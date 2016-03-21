#include <iostream>

using namespace std;

int main()
{
	int n;
	cin >> n;

	bool killer[n + 1];
	for (int i = 0; i < n + 1; i++)
		killer[i] = true;

	int parent;
	for (int i = 0; i < n; i++) {
		cin >> parent;
		killer[parent] = false;
	}

	for (int i = 1; i < n + 1; i++) {
		if (killer[i])
			cout << i << " ";
	}

	cout << endl;
}