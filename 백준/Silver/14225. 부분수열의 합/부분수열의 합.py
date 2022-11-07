# 수열 S에서 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를 구하는 문제
# 비트마스크 사용
# 리스트 dp를 set에 넣어서 가장 작은 자연수를 탐색
# set에서 탐색하면 시간 복잡도가 O(1)
N = int(input())        # 수열의 크기
S = list(map(int, input().split()))    # 수열
dp = [0] * (1 << N)

for n in range(1, 1 << N) :      # 부분집합의 개수
    for i in range(N) :
        if n & 1 << i :  # i가 포함되었나?
            dp[n] += S[i]       # 부분 수열의 합

num = 1
set_dp = set(dp)
while True :
    if num not in set_dp :
        print(num)          # 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를 출력
        break
    num += 1