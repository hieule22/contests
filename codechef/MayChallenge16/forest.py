N, W, L = map(int, input().split())
heights = [0 for i in range(N)]
rates = [0 for i in range(N)]

for i in range(N):
    heights[i], rates[i] = map(int, input().split())

low = 0
high = 10**18
while low < high:
    mid = low + ((high - low) >> 1)
    total = 0
    exceed = False
    for i in range(N):
        height = heights[i] + rates[i] * mid
        if height >= L:
            total += height
            if total >= W:
                exceed = True
                break
    if exceed:
        high = mid
    else:
        low = mid + 1

print(low)

