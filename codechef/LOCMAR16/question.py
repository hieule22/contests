def lower_bound(a, value):
	low, high = 0, len(a) - 1
	if value > a[high]:
		return high + 1
	while low < high:
		mid = (low + high) / 2
		if a[mid] < value:
			low = mid + 1
		else:
			high = mid
	return low

def upper_bound(a, value):
	low, high = 0, len(a) - 1
	if value >= a[high]:
		return high + 1
	while low < high:
		mid = (low + high) / 2
		if a[mid] <= value:
			low = mid + 1
		else:
			high = mid
	return low

n = int(raw_input())
tokens = raw_input().split()
a = [int(tokens[i]) for i in range(0, n)]
a.sort();

t = int(raw_input())
for i in range(0, t):
	tokens = raw_input().split()
	low, high = int(tokens[0]), int(tokens[1])
	left = lower_bound(a, low)
	right = upper_bound(a, high)
	cnt = right - left
	if cnt > 0:
		print cnt
	else:
		print 0


