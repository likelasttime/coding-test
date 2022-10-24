import sys, heapq
input = sys.stdin.readline

N, K = map(int, input().split())    # 보석의 개수, 가방의 개수
jewels = []

for n in range(N) :
    w, v = list(map(int, input().rstrip().split()))
    jewels.append([w, v])      # 무게, 가격

bags = [int(input()) for k in range(K)]         # 가방에 담을 수 있는 최대 무게
answer = 0

# 리스트를 힙으로 변환
heapq.heapify(jewels)
heapq.heapify(bags)

tmp = []
while bags :
    bag = heapq.heappop(bags)
    while jewels and bag >= jewels[0][0] :      # 가방에 보석을 담을 수 있음
       heapq.heappush(tmp, -heapq.heappop(jewels)[1])
    if tmp :
        answer -= heapq.heappop(tmp)
    elif not jewels :
        break

print(answer)   # 보석 가격의 합의 최댓값