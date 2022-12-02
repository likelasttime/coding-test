# 정렬되어있는 두 배열 A와 B를 합친 다음 정렬해서 출력히는 문제
import sys
input = sys.stdin.readline

N, M = map(int, input().split())    # 배열 A의 크기, 배열 B의 크기(1 <= N, M <= 1,000,000)
A = list(map(int, input().split()))
B = list(map(int, input().split()))

print(*sorted(A + B))