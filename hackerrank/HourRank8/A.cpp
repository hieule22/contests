#include <iostream>
#define min(a, b) ((a < b) ? a : b)

using namespace std;

const int MAX_N = 105;
int n; 
char str[MAX_N];

int main()
{
	cin >> n >> str;
	char previous = str[0];
	int count = 0;
	for (int i = 1; i < n - 1; ) {
		if (previous == '0' && str[i] == '1' && str[i + 1] == '0') {
			count++;
			previous = '1';
			i += 2;
		} else {
			previous = str[i];
			i++;
		}
	}

	previous = str[n - 1];
	int countR = 0;
	for (int i = n - 2; i > 0; ) {
		if (previous == '0' && str[i] == '1' && str[i + 1] == '0') {
			countR++;
			previous = '1';
			i -= 2;
		} else {
			previous = str[i];
			i--;
		}
	}

	cout << min(count, countR) << endl;
}