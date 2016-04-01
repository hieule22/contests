
t = int(raw_input())
for tt in range(0, t):
	n = int(raw_input())
	packets = []
	total = 0
	line = raw_input()
	for s in line.split():
		chocolates = int(s)
		packets.append(chocolates)
		total += chocolates
	if total % n != 0:
		print("No Treat")
	else:
		moves = 0
		average = total / n
		for i in packets:
			moves += abs(i - average)
		print(moves >> 1)

