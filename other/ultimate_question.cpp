#include <cstdio>

const int NUM = 42;

int main()
{
	int a, b, c;
	scanf("%d %d %d", &a, &b, &c);

	if (a + b + c == NUM) {
		printf("%d+%d+%d", a, b, c);
	} else if (a + b * c == NUM) {
		printf("%d+%d*%d", a, b, c);
	} else if (a * b + c == NUM) {
		printf("%d*%d+%d", a, b, c);
	} else if (a * b * c == NUM) {
		printf("%d*%d*%d", a, b, c);
	} else {
		printf("This is not the ultimate question");
	}
}