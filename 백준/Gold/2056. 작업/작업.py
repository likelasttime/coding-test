# 선행 관계가 있는 모든 작업을 완료하기 위한 최소 시간 출력
from collections import defaultdict
import sys
input = sys.stdin.readline

N = int(input())    # 작업 갯수(3 <= N <= 10000)
dic = defaultdict(list)     # 작업 : [선행 작업]
time_dic = {}       # 작업 번호 : 소요 시간

for n in range(N) :
    lst = list(map(int, input().split()))
    if lst[1] == 0 :        # 선행 작업이 없을 때
        time_dic[n + 1] = lst[0]
    else :
        for i in range(2, 2+lst[1]) :
            time_dic[n+1] = lst[0]
            dic[n+1].append(lst[i])

for i in range(1, N+1) :
    max_time = 0
    if i in dic :
        for j in dic[i] :
            max_time = max(max_time, time_dic[j])   # 선행 작업 중 가장 오래 걸리는 시간
        time_dic[i] += max_time

print(max(time_dic.values()))