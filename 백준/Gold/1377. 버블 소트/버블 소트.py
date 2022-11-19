# 버블 정렬을 했을 때 정렬이 멈추는 횟수를 구하는 문제
# 정렬 후 이동거리를 구한다
import sys
input = sys.stdin.readline

N = int(input())        # 배열의 크기
A = [int(input()) for n in range(N)]
sorted_A = sorted([(A[i], i) for i in range(N)])    # (배열의 원소, 인덱스)
answer = 0

for n in range(N) :
    answer = max(answer, sorted_A[n][1] - n)

print(answer + 1)