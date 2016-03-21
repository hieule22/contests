#include <iostream>

float q_rsqrt(float num)
{
	long i;
	float x2, y;
	const float threehalfs = 1.5F;
	x2= num * 0.5F;
	y = num;
	i = * (long *) &y;
	i = 0x5f3759df - (i >> 1);
	y = * (float *) &i;
	y = y * (threehalfs - (x2 * y * y));
	y = y * (threehalfs - (x2 * y * y));
	return y;
}

int main()
{
	std::cout << "Enter a float: ";
	float num;
	std::cin >> num;
	std::cout << q_rsqrt(num) << std::endl;
}