# 첫 번째와 마지막 구슬을 제외한 구슬을 고르는 에너지의 최댓값을 구하는 문제
# 재귀
# 제거한 구슬을 return 후 다시 제자리에 삽입한다.
N = int(input())    # 에너지 구슬 개수
W = list(map(int, input().split()))  # 에너지 구슬의 무게
answer = 0      # 에너지의 최댓값

def dfs(power) :
    global answer

    if len(W) == 2 :
        answer = max(answer, power)
        return

    for n in range(1, len(W) - 1):      # 처음과 마지막은 제외
        energy = W[n - 1] * W[n + 1]
        value = W.pop(n)
        dfs(power + energy)
        W.insert(n, value)

dfs(0)

print(answer)