# 섞으면 안 되는 조합을 고려해 아이스크림을 3가지 선택하는 방법을 구하는 문제
# 딕셔너리에 섞으면 안 되는 조합을 양방향으로 넣는다
from collections import defaultdict
import sys
input = sys.stdin.readline

N, M = map(int, input().split())        # 아이스크림 종류의 수, 섞으면 안 되는 조합의 개수
dic = defaultdict(list)
for m in range(M) :
    a, b = map(int, input().split())
    dic[a].append(b)
    dic[b].append(a)
answer = 0

# cur이 지금까지의 조랍 icecreams와 섞어도 되는지 확인
def check(icecreams, cur) :
    for icecream in icecreams :
        if cur in dic[icecream] :
            return False
    return True     # cur을 icecreams에 추가할 수 있다

def dfs(icecreams, start) :
    global answer
    if len(icecreams) == 3 :
        answer += 1
        return
    for n in range(start, N+1) :
        if n not in icecreams and check(icecreams, n) :
            icecreams.add(n)
            dfs(icecreams, n)
            icecreams.remove(n)

dfs(set(), 1)

print(answer)       # 가능한 방법의 개수