N, M = map(int, input().split())        # N까지 자연수, M개 고름

def dfs(depth, tmp, start) :    # 길이, 수열, 시작점
    if depth == M :
        print(*tmp, sep=" ")     # 길이가 M인 수열을 공백으로 구분해 출력
        return
    for n in range(start, N+1) :
        if n not in tmp :
            tmp.append(n)
            dfs(depth+1, tmp, n+1)
            tmp.remove(n)

dfs(0, [], 1)