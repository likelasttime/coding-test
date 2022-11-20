# 화면에 S개의 이모티콘을 출력하기 위해 걸리는 최소 시간을 구하는 문제
# BFS 탐색
# visit의 행은 화면, 열은 클립보드
from collections import deque
S = int(input())    # 필요한 이모티콘 개수
visit = [[-1] * (S + 1) for s in range(S + 1)]
answer = float('inf')

def bfs() :
    que = deque([(1, 0)])
    visit[1][0] = 0     # 화면에 이모티콘 1개로 시작

    while que :
        s, c = que.popleft()    # 화면, 클립보드

        if 0 <= s < S and visit[s][s] == -1 :       # 클립보드에 화면에 있는 이모티콘을 저장
            visit[s][s] = visit[s][c] + 1
            que.append((s, s))

        if s + c <= S and visit[s + c][c] == -1 :       # 클립보드에 있는 이모티콘을 화면에 붙여넣기
            visit[s + c][c] = visit[s][c] + 1
            que.append((s + c, c))

        if s - 1 >= 0 and visit[s - 1][c] == -1 :       # 화면에서 이모티콘 하나를 삭제
            visit[s - 1][c] = visit[s][c] + 1
            que.append((s - 1, c))

bfs()

for i in range(S) :
    if visit[S][i] != -1 :
        answer = min(answer, visit[S][i])

print(answer)       # 이모티콘 S개를 만들기 위해 필요한 시간의 최솟값