import sys, heapq as hq
input = sys.stdin.readline

V, E = map(int, input().split())        # 마을, 도로
graph = [[] for _ in range(V + 1)]
dist = [[1e9] * (V + 1) for _ in range(V + 1)]
heap = []
hq.heapify(heap)

for e in range(E) :
    a, b, c = map(int, input().split())     # a -> b로 가는데 c만큼의 도로 길이
    graph[a].append([c, b])
    dist[a][b] = c
    hq.heappush(heap, [c, a, b])

while heap :
    c, a, b = hq.heappop(heap)

    if a == b :
        print(c)
        break

    if dist[a][b] < c :
        continue

    for destination in graph[b] :
        dc, db = destination
        if dist[a][db] > dc + c :
            dist[a][db] = dc + c
            hq.heappush(heap, [dc + c, a, db])

else :
    print(-1)