# 틀렸습니다
import sys
input = sys.stdin.readline

n = int(input())
nums = list(map(int, input().split()))
dp = [[0] * n for _ in range(2)]
dp[0][0], dp[1][0] = nums[0], nums[0]

if n > 1 :
    for i in range(1, n) :
        dp[0][i] = max(dp[0][i - 1] + nums[i], nums[i])
        dp[1][i] = max(dp[0][i - 1], dp[1][i - 1] + nums[i])

    print(max(map(max, dp)))
else :
    print(dp[0][0])