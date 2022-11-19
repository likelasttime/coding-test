# 트리의 지름을 구하는 문제
# 1번 노드에서 가장 먼 노드를 구하고 dfs를 다시 호출해서 거리를 구했다
from collections import defaultdict
import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

V = int(input())        # 정점의 개수
answer = 0
dic = defaultdict(set)      # 정점A : 정점A와 연결된 정점들
distance_dic = dict()       # (정점A, 정점B) : 정점A와 정점B 사이의 거리
visit = [0] * (V+1)
node = 1
for v in range(V) :
    input_lst = list(map(int, input().split()))
    node = input_lst[0]
    for i in range(1, len(input_lst)-1, 2) :
        dic[node].add(input_lst[i])
        distance_dic[(node, input_lst[i])] = input_lst[i+1]
        distance_dic[(input_lst[i], node)] = input_lst[i+1]

def dfs(cur, distance) :
    global answer, node

    if answer < distance :
        answer = max(answer, distance)
        node = cur

    for val in dic[cur] :
        if not visit[val] :
            visit[val] = 1
            dfs(val, distance + distance_dic[(cur, val)])
            visit[val] = 0

visit[1] = 1
dfs(1, 0)
visit[1] = 0
visit[node] = 1
dfs(node, 0)

print(answer)       # 트리의 지름