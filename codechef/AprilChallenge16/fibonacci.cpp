#include <stdio.h>
#include <string.h>

typedef unsigned long long uint64;
const uint64 MOD = 1e9 + 7;

void multiply(uint64 mat[2][2], uint64 lhs[2][2]);
void power(uint64 ret[2][2], uint64 n);
uint64 power(uint64, uint64);
void invert(uint64 mat[2][2]);

class FenwichTree {
private:
	int n;
	uint64 (*tree)[2][2];

public:
	FenwichTree(int n): n(n) {
		tree = new uint64[n + 1][2][2];
		for (int i = 0; i <= n; i++) {
			tree[i][0][0] = tree[i][1][1] = 1;
			tree[i][1][0] = tree[i][0][1] = 0;
		}
	}

	void read(uint64 res[2][2], int index) {
		res[0][0] = res[1][1] = 1;
		res[1][0] = res[0][1] = 0;
		while (index > 0) {
			multiply(res, tree[index]);
			index -= (index & -index);
		}
	}

	void read(uint64 res[2][2], int left, int right) {
		uint64 low[2][2];
		read(low, left - 1);
		read(res, right);
		invert(low);
		multiply(res, low);
	}

	void update(int index, uint64 mat[2][2]) {
		while (index <= n) {
			multiply(tree[index], mat);
			index += (index & -index);
		}
	}
};

int main()
{
	int n, m;
	scanf("%d %d", &n, &m);
	uint64 a[n + 1];

	uint64 mat[2][2];
	FenwichTree fw(n);
	for (int i = 1; i <= n; i++) {
		scanf("%lld", a + i);
		power(mat, a[i]);
		mat[0][0] = (mat[0][0] + 1) % MOD;
		mat[1][1] = (mat[1][1] + 1) % MOD;
		fw.update(i, mat);
	}

	char query;
	uint64 x, y;
	for (int q = 0; q < m; q++)
	{
		scanf("%s %lld %lld", &query, &x, &y);
		if (query == 'C') {
			power(mat, a[x]);
			mat[0][0] = (mat[0][0] + 1) % MOD;
			mat[1][1] = (mat[1][1] + 1) % MOD;
			invert(mat);
			fw.update(x, mat);

			power(mat, y);
			
			mat[0][0] = (mat[0][0] + 1) % MOD;
			mat[1][1] = (mat[1][1] + 1) % MOD;
			fw.update(x, mat);
			a[x] = y;
		} else {
			uint64 res[2][2];
			fw.read(res, x, y);
			printf("%lld\n", res[0][1]);
		}
	}
}

void multiply(uint64 mat[2][2], uint64 lhs[2][2])
{
	uint64 tmp[2][2] = {{0, 0}, {0, 0}};
	for (int i = 0; i < 2; i++) 
		for (int j = 0; j < 2; j++)
			for (int k = 0; k < 2; k++)
				tmp[i][j] = (tmp[i][j] + mat[i][k] * lhs[k][j]) % MOD;

	for (int i = 0; i < 2; i++)
		for (int j = 0; j < 2; j++)
			mat[i][j] = tmp[i][j];
}

void power(uint64 ret[2][2], uint64 n)
{
	uint64 fib[2][2] = {{1, 1}, {1, 0}};
	uint64 tmp[2][2] = {{0, 0}, {0, 0}};
	ret[0][0] = ret[1][1] = 1;
	ret[0][1] = ret[1][0] = 0;

	int i, j, k;
	while (n) 
	{
		if (n & 1)
		{
			memset(tmp, 0, sizeof tmp);
			for (i = 0; i < 2; i++) 
				for (j = 0; j < 2; j++)
					for (k = 0; k < 2; k++)
						tmp[i][j] = (tmp[i][j] + ret[i][k] * fib[k][j]) % MOD;
			for (i = 0; i < 2; i++)
				for (j = 0; j < 2; j++)
					ret[i][j] = tmp[i][j];
		}
		memset(tmp, 0, sizeof tmp);
		for (i = 0; i < 2; i++) 
			for (j = 0; j < 2; j++) 
				for (k = 0; k < 2; k++)
					tmp[i][j] = (tmp[i][j] + fib[i][k] * fib[k][j]) % MOD;
		for (i = 0; i < 2; i++)
			for (j = 0; j < 2; j++)
				fib[i][j] = tmp[i][j];
		n /= 2;
	}
}

void invert(uint64 mat[2][2])
{
	uint64 det = (mat[1][0] * mat[0][1]) % MOD;
	det = MOD - det;
	det = (mat[0][0] * mat[1][1] + det) % MOD;
	det = power(det, MOD - 2);

	uint64 a = mat[0][0], b = mat[0][1], c = mat[1][0], d = mat[1][1];
	mat[0][0] = (det * d) % MOD;
	mat[0][1] = (det * (MOD - b)) % MOD;
	mat[1][0] = (det * (MOD - c)) % MOD;
	mat[1][1] = (det * a) % MOD;
}

uint64 power(uint64 a, uint64 b)
{
	if (b == 0)
		return 1;
	uint64 res = power(a, b / 2);
	res = (res * res) % MOD;
	if (b % 2 == 1)
		res = (res * a) % MOD;
	return res;
}