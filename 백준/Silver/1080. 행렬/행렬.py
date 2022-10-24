import sys
input = sys.stdin.readline

N, M = map(int, input().split())

A = [list(map(int, list(input().rstrip()))) for _ in range(N)]
B = [list(map(int, list(input().rstrip()))) for _ in range(N)]

answer = 0

def reverse(i, j) :
    for x in range(i, i+3) :
        for y in range(j, j+3) :
            A[x][y] = 1 - A[x][y]       # 0은 1로, 1은 0으로 뒤집기

for i in range(N-2) :
    for j in range(M-2) :
        if A[i][j] != B[i][j] :
            reverse(i, j)
            answer += 1

for i in range(N) :
    if A[i] != B[i] :
        print("-1")    # A를 B로 절대 바꿀 수 없다
        break
else :
    print(answer)    # A를 B로 바꾸는 데 필요한 연산의 횟수의 최솟값