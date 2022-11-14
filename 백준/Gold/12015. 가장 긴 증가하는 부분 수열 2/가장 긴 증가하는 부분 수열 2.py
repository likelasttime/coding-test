# 수열에서 가장 긴 증가하는 부분 수열의 길이를 구하는 문제
N = int(input())    # 수열의 크기
A = list(map(int, input().split()))
lst = [0]

for a in A :
    if lst[-1] < a :
        lst.append(a)
    else :      # 이분탐색을 이용해 a를 lst에서 알맞은 자리에 넣기
        left, right = 0, len(lst)
        while left < right :
            mid = (left + right) // 2
            if lst[mid] < a :
                left = mid + 1
            else :
                right = mid
        lst[left] = a

print(len(lst) - 1)