#include <iostream>
#include <stdio.h>
#include <vector>
#include <cmath>

using namespace std;

class Point
{
public:
	int x, y, z;

	Point(int x, int y, int z)
		: x(x), y(y), z(z) {}
};

int main()
{
	int n;
	cin >> n;
	vector<Point> points;
	int x, y, z;
	for (int i = 0; i < n; i++) {
		cin >> x >> y >> z;
		points.push_back(Point(x, y, z));
	}

	double total = 0;
	for (int i = 0; i < n - 2; i++) {
		for (int j = i + 1; j < n - 1; j++) {
			for (int k = j + 1; k < n; k++) {
				int dx1 = points[i].x - points[j].x;
				int dx2 = points[k].x - points[j].x;
				int dy1 = points[i].y - points[j].y;
				int dy2 = points[k].y - points[j].y;
				int dz1 = points[i].z - points[j].z;
				int dz2 = points[k].z - points[j].z;
				double dotProduct = dx1 * dx2 + dy1 * dy2 + dz1 * dz2;
				double cosine = dotProduct / (sqrt(dx1 * dx1 + dy1 * dy1 + dz1 * dz1) * 
												sqrt(dx2 * dx2 + dy2 * dy2 + dz2 * dz2));
				total += acos(cosine);
			}
		}
	}

	printf("%1.11f\n", total / (n * (n - 1) * (n - 2) / 6));
	// cout << total / (n * (n - 1) * (n - 2) / 6) << endl;
}