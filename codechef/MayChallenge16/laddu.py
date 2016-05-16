tt = int(input())

for t in range(tt):
    tokens = input().split()
    n = int(tokens[0])
    origin = tokens[1]

    score = 0
    for i in range(n):
        tokens = input().split()
        activity = tokens[0]
        if activity == 'CONTEST_WON':
            rank = int(tokens[1])
            score += 300
            if rank <= 20:
                score += 20 - rank
        elif activity == 'TOP_CONTRIBUTOR':
            score += 300
        elif activity == 'BUG_FOUND':
            severity = int(tokens[1])
            score += severity
        else:
            score += 50

    res = 0
    if origin == 'INDIAN':
        res = int(score / 200)
    else:
        res = int(score / 400)
    print(res)

