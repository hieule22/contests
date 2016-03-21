#include "vector.h"
#include <iostream>

int main()
{
	vector<int> v(10);
	for (int i = 0; i < v.size(); i++)
		v[i] = i;

	for (int i = v.size() - 1; i >= 0; i--)
		std::cout << v[i] << std::endl;
}