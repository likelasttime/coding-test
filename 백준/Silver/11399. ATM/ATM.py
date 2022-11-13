# 각 사람이 돈을 인출하는데 필요한 시간의 합의 최솟값을 구하는 문제
# 오름차순 정렬 후 더하는 것을 반복
N = int(input())        # 사람의 수
P = list(map(int, input().split()))
answer = 0

P.sort()        # 오름차순 정렬

for n in range(N) :
    answer += sum(P[ : n+1])

print(answer)       # 필요한 시간의 합의 최솟값