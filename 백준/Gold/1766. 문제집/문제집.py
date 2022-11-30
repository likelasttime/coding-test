# 먼저 문제를 풀어야하는 순서대로 출력
# 위상정렬
import sys
import heapq

input = sys.stdin.readline
N, M = map(int, input().split())    # 문제의 수, 정보의 개수
info = [[] for n in range(N+1)]     # 행은 리스트 값의 선수과목
cnt = [0] * (N+1)        # 값은 인덱스의 선수과목 개수
answer = []
que = []

for m in range(M) :
    a, b = map(int, input().split())
    info[a].append(b)
    cnt[b] += 1

for n in range(1, N+1) :
    if not cnt[n] :
        heapq.heappush(que, n)

while que :
    num = heapq.heappop(que)   # 더이상 선수과목이 없는 과목을 pop
    answer.append(num)
    for val in info[num] :
        cnt[val] -= 1
        if not cnt[val] :   # val의 선수과목을 모두 수강
            heapq.heappush(que, val)

print(*answer)