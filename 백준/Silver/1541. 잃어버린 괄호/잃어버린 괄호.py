# +와 -로 이루어진 수식에서 적절히 괄호를 쳐서 최소값을 만드는 문제
# 수식을 -를 기준으로 나눈다
# +가 있으면 총합을 구한다
# lst에 값을 넣고 앞에서부터 뺀다
import sys
from collections import deque
input = sys.stdin.readline

expression = input().rstrip()
split_expression = deque(expression.split('-'))
lst = []

while split_expression :
    s = split_expression.popleft()
    if '+' in s :
        split_s = list(map(int, s.split('+')))
        lst.append(sum(split_s))
    else :
        lst.append(int(s))

answer = lst[0]
for i in range(1, len(lst)) :
    answer -= lst[i]

print(answer)