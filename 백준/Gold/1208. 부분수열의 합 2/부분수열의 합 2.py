# 합이 S가 되는 부분수열의 개수를 구하는 문제
# 수열을 왼쪽, 오른쪽으로 나누고, 조합을 생성하고 합을 구한다
# 오른쪽에서 이분탐색한 값과 왼쪽에서 이분탐색한 값의 차이는 S의 개수다
from itertools import combinations
from bisect import bisect_left, bisect_right
N, S = map(int, input().split())    # 정수의 개수(1 <= N <= 40), 정수(|S| <= 1,000,000)
numbers = list(map(int, input().split()))       # 수열
len_numbers = len(numbers)
answer = 0

def get_cnt(lst, S) :
    return bisect_right(lst, S) - bisect_left(lst, S)

def make_combinations(lst) :
    sum_lst = []
    for i in range(1, len(lst)+1) :
        for j in list(combinations(lst, i)) :
            sum_lst.append(sum(j))
    return sorted(sum_lst)

left, right = numbers[ : len_numbers//2], numbers[len_numbers//2 : ]
sum_left = make_combinations(left)
sum_right = make_combinations(right)

for i in sum_left :
    num = S - i
    answer += get_cnt(sum_right, num)

answer += get_cnt(sum_left, S)
answer += get_cnt(sum_right, S)

print(answer)       # 합이 S가 되는 부분수열의 개수를 출력
