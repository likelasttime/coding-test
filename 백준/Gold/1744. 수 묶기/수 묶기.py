import sys
input = sys.stdin.readline

N = int(input())
positive = []
negative = []
answer = 0

for n in range(N) :
    num = int(input())
    if num > 1 :
        positive.append(num)
    elif num == 1 :
        answer += 1
    else :
        negative.append(num)

positive.sort(reverse=True)     # 양수는 내림차순 정렬
negative.sort()                 # 음수는 오름차순 정렬

def even(lst, answer, size) :
    for i in range(0, size, 2) :
        answer += (lst[i] * lst[i+1])
    return answer

def odd(lst, answer, size) :
    for i in range(0, size-1, 2) :
        answer += (lst[i] * lst[i+1])
    answer += lst[-1]
    return answer

len_positive = len(positive)
len_negative = len(negative)

if len_positive % 2 == 0 :
    answer = even(positive, answer, len_positive)
else :
    answer = odd(positive, answer, len_positive)

if len_negative % 2 == 0 :
    answer = even(negative, answer, len_negative)
else :
    answer = odd(negative, answer, len_negative)

print(answer)