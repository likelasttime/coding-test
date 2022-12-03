# 트리가 주어진 올바른 BFS 방문 순서라면 1을 출력하는 문제
# BFS를 이용해 노드별 자식 노드를 구한다
# set을 이용해 방문한 노드의 개수만큼 방문 순서를 비교했다
from collections import deque
import sys
input = sys.stdin.readline

N = int(input())        # 정점의 개수(2 <= N <= 100,000)
tree = [[] for n in range(N+1)]
for n in range(N-1) :
    a, b = map(int, input().split())
    tree[a].append(b)
    tree[b].append(a)

visit_order = list(map(int, input().split()))        # BFS 방문 순서
connection_tree = [[] for n in range(N+1)]

def bfs() :
    q = deque([1])
    visited = [0] * (N+1)
    while q :
        x = q.popleft()
        visited[x] = 1
        for y in tree[x] :
            if not visited[y] :
                q.append(y)
                connection_tree[x].append(y)

if visit_order[0] == 1 :
    bfs()
    que = deque([1])
    idx = 1
    while que :
        x = que.popleft()
        child_x = set(connection_tree[x])   # x의 자식 노드들
        len_childX = len(child_x)
        input_order = visit_order[idx : idx + len_childX]
        set_input_order = set(input_order)
        idx += len_childX
        que.extend(input_order)
        if child_x != set_input_order :
            print(0)
            break
    else :
        print(1)
else :          # 1부터 시작하지 않는 입력 순서
    print(0)