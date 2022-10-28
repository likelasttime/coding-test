import sys
input = sys.stdin.readline

N = int(input())
positive = []
negative = []
answer = 0

for _ in range(N) :
    n = int(input())

    if n > 1 :      # 양수
        positive.append(n)
    elif n == 1 :
        answer += 1
    else :
        negative.append(n)

positive.sort(reverse = True)       # 내림차순 정렬
negative.sort()

if len(positive) % 2 == 0 :     # 짝수 개만큼 있을 때
    for i in range(0, len(positive), 2) :
        answer += positive[i] * positive[i+1]
else :
    for i in range(0, len(positive)-1, 2) :
        answer += positive[i] * positive[i+1]
    answer += positive[len(positive)-1]

if len(negative) % 2 == 0 :     # 짝수 개만큼 있을 때
    for i in range(0, len(negative), 2) :
        answer += negative[i] * negative[i+1]
else :
    for i in range(0, len(negative)-1, 2) :
        answer += negative[i] * negative[i+1]
    answer += negative[len(negative)-1]

print(answer)

