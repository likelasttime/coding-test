# 수빈이가 동생을 구할 수 있는 가장 빠른 시간과 이동 과정을 구하는 문제
from collections import deque
N, K = map(int, input().split())        # 수빈이가 있는 위치, 동생이 있는 위치(0 <= N, K <= 100,000)
visit = [0] * 100001        # 인덱스 = 위치, 값 = 이전 위치
depth = [0] * 100001        # 인덱스 = 위치, 값 = 시간
answer = []

def bfs() :
    que = deque([N])
    while que :
        cur = que.popleft()
        if cur == K :
            print(depth[cur])       # 동생을 찾는 가장 빠른 시간을 출력
            break
        for next_pos in [cur-1, cur+1, cur*2] :
            if 0 <= next_pos <= 100000 and not depth[next_pos] :
                visit[next_pos] = cur
                depth[next_pos] = depth[cur] + 1
                que.append(next_pos)

bfs()

idx = K
for i in range(depth[K] + 1) :
    answer.append(idx)
    idx = visit[idx]

print(' '.join(map(str, answer[::-1])))     # 어떻게 이동하는지를 출력