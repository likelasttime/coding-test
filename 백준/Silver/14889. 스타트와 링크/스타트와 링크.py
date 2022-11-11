# 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 구하는 문제
# 스타트 팀과 링크 팀의 팀원 수는 같아야 한다
# 재귀
# 두 팀의 팀원수가 같지 않으면 return
import sys
input = sys.stdin.readline

N = int(input())        # 짝수
powers = [list(map(int, input().split())) for n in range(N)]
answer = float('inf')

def dfs(start, link, depth) :
    global answer

    total_start = 0
    total_link = 0

    if depth == N :
        if len(link) != len(start) != N // 2 :      # 두 팀의 팀원 수가 같지 않다면
            return
        
        # 두 팀의 능력치를 계산
        for i in range(N//2 - 1) :
            for j in range(i + 1, N//2) :
                si, sj = start[i], start[j]
                li, lj = link[i], link[j]
                total_start += powers[si][sj] + powers[sj][si]
                total_link += powers[li][lj] + powers[lj][li]

        answer = min(answer, abs(total_start - total_link))     
        return

    dfs(start + [depth], link, depth + 1)
    dfs(start, link + [depth], depth + 1)

dfs([], [], 0)
print(answer)