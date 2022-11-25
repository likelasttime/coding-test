# S를 T로 만들 수 있는지 구하는 문제
# 재귀를 이용
# S에서 T를 만드는 것보다 T에서 S를 만드는 게 훨씬 간단하다
S = input()
T = input()
len_t = len(T)
len_s = len(S)
answer = False

def dfs(depth, cur_t) :
    global answer
    if depth == len_s :
        if cur_t == S :
            answer = True
        return

    if depth == 0 :
        return

    if cur_t[-1] == 'A' :
        dfs(depth - 1, cur_t[:-1])
    if cur_t[0] == 'B' :
        dfs(depth - 1, (cur_t[1:])[::-1])

dfs(len_t, T)

if answer :
    print(1)
else :
    print(0)