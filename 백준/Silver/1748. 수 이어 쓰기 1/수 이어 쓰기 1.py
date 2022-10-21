import sys
input = sys.stdin.readline

N = input().rstrip()
answer = 0
size_n = len(N)

for i in range(size_n-1) :
    answer += 9 * (10**i) * (i+1)       # 9개 * 10의 자리수 * 길이

remainder = (int(N) - 10**(size_n-1)) * size_n + size_n     # 아직 계산하지 않은 수 * 길이 + 10**(size_n-1)의 길이 
print(answer + remainder)