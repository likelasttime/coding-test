# 배열 A를 오름차순 정렬했을 때 k번째 값을 구하는 문제
# 이분탐색
N = int(input())        # 배열의 크기
k = int(input())
answer = 0
left, right = 1, k

while left <= right :
    mid = (left + right) // 2

    cnt = 0
    for i in range(1, N+1) :
        cnt += min(mid // i, N)

    if cnt >= k :
        answer = mid
        right = mid - 1
    else :
        left = mid + 1

print(answer)