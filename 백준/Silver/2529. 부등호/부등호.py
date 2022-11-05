# 0~9까지의 숫자를 중복되지 않게 사용해 부등호 순서열을 만족시키는 최소값, 최대값을 구하는 문제
# 재귀를 사용해서 K+1 길이의 수를 만들고 부등호를 적용해서 체크한다
def isPass(x, y, op) :
    if op == '<' :
        if x > y :
            return False
    if op == '>' :
        if x < y :
            return False
    return True

def dfs(index, num) :
    if index == n + 1 :     # 숫자는 n + 1개 필요
        ans.append(num)
        return

    for i in range(10) :
        if i in check : continue      # 해당 숫자를 이미 사용했다면 pass

        if index == 0 or isPass(num[index-1], str(i), a[index - 1]) :
            check.add(i)
            dfs(index + 1, num + str(i))
            check.remove(i)

n = int(input())
a = input().split()

ans = []
check = set()        # 숫자가 중복되는지 확인하기 위해 사용

dfs(0, '')

ans.sort()
print(ans[-1])      # 최댓값
print(ans[0])       # 최솟값