import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

N, M, T = map(int, input().split())  # 반지름, 정수의 개수, 회전 횟수
circles = [list(map(int, input().split())) for n in range(N)]  # 원판
rotations = [list(map(int, input().split())) for t in range(T)]  # 회전 정보
cnt_nums = N * M

def right(i, k):
    tmp = [0] * M
    for idx in range(M):
        tmp[(idx + k) % M] = circles[i][idx]
    return tmp

def left(i, k):
    tmp = [0] * M
    for idx in range(M):
        tmp[(idx - k) % M] = circles[i][idx]
    return tmp

def remove_two(i1, j1, i2, j2):
    circles[i1][j1] = 0
    circles[i2][j2] = 0

def dfs(i, j, value):
    global flag
    for ni, nj in [(i, j-1), (i, j+1), (i-1, j), (i+1, j)] :
        if ni == -1 or ni == N : continue
        if circles[ni % N][nj % M] == value :
            flag = True
            circles[ni % N][nj % M] = 0
            dfs(ni % N, nj % M, value)

def total():
    answer = 0
    cnt_zero = 0
    for i in range(N):
        for j in range(M):
            if circles[i][j] == 0:
                cnt_zero += 1
                continue
            answer += circles[i][j]
    return answer, cnt_zero

def none_result(average):
    for i in range(N):
        for j in range(M):
            if circles[i][j] == 0:
                continue
            # 평균보다 크거나 작을 때
            if circles[i][j] > average:
                circles[i][j] -= 1
            elif circles[i][j] < average:
                circles[i][j] += 1

for x, d, k in rotations:
    for i in range(x - 1, N, x):
        if d == 0:  # 시계 방향
            circles[i] = right(i, k)
        else:
            circles[i] = left(i, k)
    flag = False
    for i in range(N) :
        for j in range(M) :
            if circles[i][j] != 0 :
                dfs(i, j, circles[i][j])
    if not flag :       # 삭제한 수가 없을 때
        sum_circles, cnt_zero = total()
        if sum_circles == 0 : continue
        average = sum_circles / (cnt_nums - cnt_zero)
        none_result(average)

print(total()[0])