# 수열 A에서 각 원소가 등장한 횟수보다 큰 수 중에서 가장 왼쪽에 있는 수를 구하는 문제
# Counter를 이용해서 각 원소의 등장 횟수를 쉽게 구했다
# 스택을 이용해서 top에 있는 원소와 수열 A의 원소를 비교
from collections import Counter
N = int(input())    # 수열 A의 크기(1 <= N <= 1,000,000)
A = list(map(int, input().split()))
answer = [-1] * N
counter = Counter(A)
stack = [0]

for i in range(1, N) :
    while stack and counter[A[stack[-1]]] < counter[A[i]] :
        answer[stack.pop()] = A[i]
    stack.append(i)

print(*answer)