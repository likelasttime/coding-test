def solution(picks, minerals):
    answer = 10 ** 9
    len_minerals = len(minerals)
    dic = {}
    dic["diamond"] = 0
    dic["iron"] = 1
    dic["stone"] = 2
    arr = [[1, 1, 1], [5, 1, 1], [25, 5, 1]]

    def dfs(d, i, s, cur, total):
        nonlocal answer
        if cur >= len_minerals or (d == 0 and i == 0 and s == 0):
            answer = min(answer, total)
            return

        dv, iv, sv = 0, 0, 0
        for x in range(cur, min(cur + 5, len_minerals)):
            dv += arr[0][dic[minerals[x]]]
            iv += arr[1][dic[minerals[x]]]
            sv += arr[2][dic[minerals[x]]]

        if d:
            dfs(d - 1, i, s, cur + 5, total + dv)
        if i:
            dfs(d, i - 1, s, cur + 5, total + iv)
        if s:
            dfs(d, i, s - 1, cur + 5, total + sv)

    dfs(picks[0], picks[1], picks[2], 0, 0)

    return answer