from collections import defaultdict, deque

def solution(n, roads, sources, destination):
    answer = []
    dic = defaultdict(list)
    check = [-1] * (n + 1)

    for s, e in roads:
        dic[s].append(e)
        dic[e].append(s)

    def bfs(start):
        nonlocal check
        que = deque([(start, 0)])
        while que:
            cur, distance = que.popleft()
            for k in dic[cur]:
                if check[k] != -1 : continue
                check[k] = distance + 1
                que.append((k, distance + 1))

    check[destination] = 0
    bfs(destination)

    for source in sources:
        answer.append(check[source])

    return answer  # 최단 시간 배열