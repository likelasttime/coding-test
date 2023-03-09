import sys
input = sys.stdin.readline

a = input().rstrip()
b = input().rstrip()

len_a = len(a)
len_b = len(b)
dp = [[0] * (len_a + 1) for _ in range(len_b + 1)]

if len_a * len_b == 0 :
    print(0)
else :
    for i in range(1, len_b + 1) :
        for j in range(1, len_a + 1) :
            if a[j-1] == b[i-1] :
                dp[i][j] = dp[i-1][j-1] + 1
            else :
                dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    print(dp[-1][-1])