import sys
input = sys.stdin.readline

T = int(input())

def cal(m, n, x, y) :
    k = x
    while k <= m * n :
        if (k - x) % m == 0 and (k - y) % n == 0 :
            return k
        k += m
    return -1

for t in range(T) :
    M, N, x, y = map(int, input().rstrip().split())
    print(cal(M, N, x, y))