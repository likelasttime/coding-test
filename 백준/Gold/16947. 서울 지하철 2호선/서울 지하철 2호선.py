# 각 역과 순환선 사이의 거리를 구하는 문제
# 재귀를 이용해 순환선을 찾는다
from collections import defaultdict, deque
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**4)

N = int(input())    # 역의 개수
dic = defaultdict(list)
answer = [-1] * (N+1)
check = [0] * (N+1)

for n in range(N) :
    a, b = map(int, input().split())
    dic[a].append(b)
    dic[b].append(a)

# 순환선 찾기
def check_circulation(start, cur, cnt) :
    global flag

    if start == cur and cnt >= 2 :      # 출발점으로 다시 돌아왔을 때
        flag = True
        return

    visit[cur] = 1

    for val in dic[cur] :
        if not visit[val] :
            check_circulation(start, val, cnt + 1)
        elif val == start and cnt >= 2 :        # 출발점으로 다시 돌아왔을 때
            check_circulation(start, val, cnt)

# 순환역까지의 거리 계산
def cal_distance() :
    que = deque()

    for n in range(1, N+1) :
        if check[n] :      # 순환역에 속하면
            answer[n] = 0
            que.append(n)

    while que :
        cur = que.popleft()
        for i in dic[cur] :
            if answer[i] == -1 :        # 순환역이 아닌 역
                que.append(i)
                answer[i] = answer[cur] + 1

    print(*answer[1:], sep=' ')

# 순환 노선 찾기
for key in dic :
    visit = [0] * (N + 1)
    flag = False
    check_circulation(key, key, 0)
    if flag:  # 순환선이 존재한다면
        check[key] = 1

cal_distance()
