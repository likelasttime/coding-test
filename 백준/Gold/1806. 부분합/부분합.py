# 수열에서 연속된 수들의 부분합 중에 합이 S 이상이 되는 것 중 가장 짧은 것의 길이를 구하는 문제
# 누적합을 구한 후 투포인터 사용
N, S = map(int, input().split())        # 길이, 합
lst = list(map(int, input().split()))
cumulative_sum = [0] * (N+1)
answer = N
start, end = 0, 1

if sum(lst) < S :       # 불가능
    print(0)
else :
    # 누적합 구하기
    for n in range(1, N+1):
        cumulative_sum[n] = cumulative_sum[n - 1] + lst[n-1]

    # 투포인터
    while start != N :
        if cumulative_sum[end] - cumulative_sum[start] >= S :
            answer = min(answer, end - start)
            start += 1
        else :      # 합이 S보다 작으면 오른쪽으로 이동
            if end != N :
                end += 1
            else :      # end가 끝에 도달해서 start를 움직인다
                start += 1

    print(answer)