# 하루에 최대 한 곳에서 강연하면서 최대로 벌 수 있는 돈을 출력하는 문제
# d일이 빠른 순으로 탐색
# 강연을 할 곳의 수가 현재 d일 보다 더 크면 힙에서 가장 작은 강연료를 뺀다.

import sys, heapq
input = sys.stdin.readline

n = int(input())
lst = []
for _ in range(n):
    p, d = list(map(int, input().split()))
    lst.append([d, p])

heapq.heapify(lst)
total_p = []

while lst :
    d, p = heapq.heappop(lst)
    heapq.heappush(total_p, p)
    if len(total_p) > d :
        heapq.heappop(total_p)

print(sum(total_p))