import sys
input = sys.stdin.readline

N, M = map(int, input().split())        # 세로, 가로
matrix = [list(map(int, input().rstrip().split())) for _ in range(N)]

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
answer = 0
max_num = max(map(max, matrix))

def dfs(depth, total, visit, x, y) :
    global answer
    if answer >= total + max_num * (4-depth) :
        return
    if depth == 4 :
        answer = max(answer, total)
        return
    else :
        for i in range(4) :
            nx, ny = dx[i] + x, dy[i] + y
            if 0 <= nx < N and 0 <= ny < M :
                if (nx, ny) not in visit :
                    if depth == 2 :     # ㅜ, ㅏ, ㅓ, ㅗ 모양 처리
                        visit.add((nx, ny))
                        dfs(depth + 1, total + matrix[nx][ny], visit, x, y)
                        visit.remove((nx, ny))

                    visit.add((nx, ny))
                    dfs(depth+1, total + matrix[nx][ny], visit, nx, ny)
                    visit.remove((nx, ny))

for i in range(N) :
    for j in range(M) :
        dfs(1, matrix[i][j], set([(i, j)]), i, j)

print(answer)