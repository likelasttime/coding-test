# 임의의 위치에서 인접해 있는 네 방향으로 다섯 번 이동하면서 만들 수 있는 서로 다른 여섯 자리의 수들의 개수를 구하는 문제
def dfs(x, y, number) :
    if len(number) == 6 :
        if number not in result :
            result.append(number)
        return

    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    for k in range(4) :
        nx = x + dx[k]
        ny = y + dy[k]

        if 0 <= nx < 5 and 0 <= ny < 5 :
            dfs(nx, ny, number + matrix[nx][ny])

matrix = [list(map(str, input().split())) for _ in range(5)]
result = []

for i in range(5) :
    for j in range(5) :
        dfs(i, j, matrix[i][j])

print(len(result))      # 만들 수 있는 서로 다른 여섯 자리의 수들의 개수