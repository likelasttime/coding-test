# 남은 N일 동안 최대 수익을 계산하는 문제
# DP
import sys
input = sys.stdin.readline

N = int(input())        # 남은 일수
info = [list(map(int, input().split())) for n in range(N)]   # [상담을 완료하는데 걸리는 기간 t, 금액 p]
dp = [0] * (N+2)

for i in range(N-1, -1, -1) :
    if info[i][0] + i > N :
        dp[i] = dp[i + 1]
    else :
        dp[i] = max(dp[i + 1], info[i][1] + dp[i + info[i][0]])

print(dp[0])