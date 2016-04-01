n = int(raw_input())
a = []
line = raw_input()
for i in line.split():
	a.append(int(i))

a.sort()
if (n == 1):
	print 0
else:
	index = n - 2
	while index >= 0 and a[index] == a[n - 1]:
		index = index - 1
	if index < 0:
		print 0
	else:
		print a[index]