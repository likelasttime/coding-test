# 문제에서 주어진 관계가 존재하는지 구하는 문제
# DFS 풀이
import sys
input = sys.stdin.readline

N, M = map(int, input().split())        # 사람의 수(5 <= N <= 2000), 친구 관계의 수(1 <= M <= 2000)
graph = [[] for _ in range(N)]
visited = [0] * 2001
answer = False

# 양방향 연결
for m in range(M) :
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def dfs(idx, depth) :
    global answer
    visited[idx] = 1
    if depth == 4 :
        answer = True
        return
    for num in graph[idx] :
        if not visited[num] :
            visited[num] = True
            dfs(num, depth + 1)
            visited[num] = False

for n in range(N) :
    dfs(n, 0)
    visited[n] = False
    if answer :
        break

if answer :
    print(1)    # 존재
else :
    print(0)    # 존재하지 않음