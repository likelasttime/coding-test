def solution(users, emoticons):
    answer = [0, 0]
    discounts = [10, 20, 30, 40]
    m = len(users)
    n = len(emoticons)
    arr = [0] * n

    def dfs(idx):
        nonlocal answer
        if idx == n:
            signup, value = 0, 0
            for x in range(m):
                tmp = 0
                for y in range(n):
                    if arr[y] >= users[x][0]:
                        tmp += emoticons[y] * (100 - arr[y]) // 100
                if tmp >= users[x][1]:
                    signup += 1         # 이모티콘 플러스 가입
                else:
                    value += tmp        # 이모티콘 구매 비용
            if signup > answer[0] or signup == answer[0] and value > answer[1] :   
                answer[0] = signup
                answer[1] = value
            return

        for i in range(4):
            arr[idx] = discounts[i]
            dfs(idx + 1)

    dfs(0)

    return answer  # [이모티콘 플러스 서비스 가입 수, 이모티콘 매출액]