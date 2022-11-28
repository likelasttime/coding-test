N = int(input())    # 상근이가 가지고 있는 숫자 카드의 개수
card = list(map(int, input().split()))
M = int(input())        # 상근이가 가지고 있는 숫자 카드인지 아닌지 구해 할 정수의 개수
ex_card = list(map(int, input().split()))
between_card = set(card) & set(ex_card)     # 중복되는 번호 집합
answer = [0] * M

for m in range(M) :
    if ex_card[m] in between_card :
        answer[m] = 1

print(*answer)