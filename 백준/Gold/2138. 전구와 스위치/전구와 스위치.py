# N개의 전구들의 현재 상태를 만들고자 하는 상태로 만들기 위해 스위치를 눌러야하는 최소 횟수를 구하는 문제
# 첫 번째 스위치를 누르는 경우와 누르지 않는 경우로 나누었다
from copy import deepcopy
N = int(input())        # 스위치와 전구의 개수
before = list(map(int, list(input())))
after = list(map(int, list(input())))
answer = float('inf')

# 첫번째 전구를 켜거나 켜지 않는 두 가지 경우를 고려
before1 = deepcopy(before)
before2 = deepcopy(before)

def push_switch_two(bulbs, x, y) :
    bulbs[x] = 1 - bulbs[x]
    bulbs[y] = 1 - bulbs[y]

def push_switch_three(bulbs, x, y, z) :
    bulbs[x] = 1 - bulbs[x]
    bulbs[y] = 1 - bulbs[y]
    bulbs[z] = 1 - bulbs[z]

def case(bulbs, start) :
    cnt = 0
    for i in range(start, N) :
        if i == 0 :     # 첫 번째 전구의 스위치
            cnt += 1
            push_switch_two(bulbs, i, i+1)
        elif i == N-1 :     # 마지막 전구의 스위치
            if bulbs[i-1] != after[i-1] :
                cnt += 1
                push_switch_two(bulbs, i, i-1)
        else :
            if bulbs[i-1] != after[i-1] :
                cnt += 1
                push_switch_three(bulbs, i-1, i, i+1)
        if bulbs == after :         # 목표한대로 전구의 상태 완성
            return True, cnt
    return False, cnt

for i in range(2) :
    if i == 0 :
        flag, cnt = case(before1, 0)        # 첫 번째 전구의 스위치를 누른다
    else :
        flag, cnt = case(before2, 1)        # 첫 번째 전구의 스위치를 누르지 않는다
    if flag :
        answer = min(answer, cnt)

if answer == float('inf') :
    answer = -1         # 실패

print(answer)       # 스위치를 최소 눌러야 하는 횟수