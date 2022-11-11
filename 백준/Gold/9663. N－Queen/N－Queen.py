# N * N인 체스판에서 N개의 퀸이 가로 or 세로 or 대각선으로 서로 공격할 수 없게 놓는 방법의 수를 구하는 문제
# 백트래킹
# 이전에 놓은 퀸과 대각선으로 위치하면 return
# visit을 N개 만큼 열을 만들고 값으로 행을 넣는다
N = int(input())
visit = [-1] * N
answer = 0

# 이전에 놓은 퀸들과 대각선 검사
def check(depth) :
    cur = visit[depth-1]
    cnt = 1
    for i in range(depth-2, -1, -1) :
        if cur - cnt == visit[i] or cur + cnt == visit[i] :     # 왼쪽 위 or 오른쪽 위에 같은 행이 있을 때
            return False
        cnt += 1
    return True

def dfs(depth) :
    global answer

    if depth > 1 and not check(depth) :     # depth개의 퀸이 서로 공격할 수 있다
        return

    if depth == N :
        answer += 1
        return

    for i in range(N) :
        if i not in visit :     # 서로 다른 N개의 행
            if visit[depth] == -1 :
                visit[depth] = i
                dfs(depth + 1)
                visit[depth] = -1

dfs(0)
print(answer)       # 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수