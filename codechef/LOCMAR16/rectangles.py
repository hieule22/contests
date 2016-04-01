t = int(raw_input())

for i in range(0, t):
	line = raw_input().split()
	n, m = int(line[0]), int(line[1])
	a = []
	for i in range(0, n):
		row = raw_input().split()
		a.append(row)

	for col in range(0, m):
		r, c = 0, col
		while r < n and c >= 0:
			print(a[r][c]),
			r, c = r + 1, c - 1

	for row in range(1, n):
		r, c = row, m - 1
		while r < n and c >= 0:
			print(a[r][c]),
			r, c = r + 1, c - 1