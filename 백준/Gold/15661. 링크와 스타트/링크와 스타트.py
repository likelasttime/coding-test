# 스타트 팀과 링크 팀의 능력치의 차이를 최소로 하는 문제
# 모든 경우를 고려해서 구현
import sys
input = sys.stdin.readline

N = int(input())    # 총 인원
S = [list(map(int, input().split())) for n in range(N)]
answer = float('inf')

# 스타트 팀 또는 링크 팀의 능력치 계산
def cal(lst) :
    result = 0
    len_lst = len(lst)
    for i in range(len_lst) :
        for j in range(i+1, len_lst) :
            if i == j: continue
            result += S[lst[i]][lst[j]] + S[lst[j]][lst[i]]
    return result

def dfs(index, start, link) :
    if index == N :     # N명을 모두 배치했을 때 능력치를 계산하게 된다
        if len(start) == N or len(link) == N :
            return -1

        start_team = cal(start)
        link_team = cal(link)

        return abs(start_team - link_team)      # 능력치의 차이

    if len(start) > N or len(link) > N : return -1

    ans = -1

    total_start = dfs(index + 1, start + [index], link)     # start팀에 index를 배정
    if ans == -1 or (total_start != -1 and total_start < ans) :
        ans = total_start

    total_link = dfs(index + 1, start, link + [index])      # link팀에 index를 배정
    if ans == -1 or (total_link != -1 and total_link < ans) :
        ans = total_link

    return ans

print(dfs(0, [], []))