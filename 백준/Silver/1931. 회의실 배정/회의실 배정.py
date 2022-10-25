import sys, heapq
input = sys.stdin.readline

N = int(input())        # 회의의 수
t_lst = []

for n in range(N) :
    s, e = map(int, input().split())
    t_lst.append([e, s])        # 끝나는 시간, 시작하는 시간

heapq.heapify(t_lst)        # 최소힙(끝나는 시간이 빠른 순)

def cal(cur_e, lst) :
    result = 0
    while lst :
        e, s = heapq.heappop(lst)       # 시작 시간, 끝나는 시간
        if e >= cur_e and s >= cur_e :  # 다음 회의 끝나는 시간 >= 현재 회의 끝난 시간 and 다음 회의 시작하는 시간 >= 현재 회의 끝난 시간
            cur_e = e
            result += 1
    return result

print(cal(0, t_lst))