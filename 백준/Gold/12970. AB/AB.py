import sys
input = sys.stdin.readline

n, k = map(int, input().split())        # 길이, 쌍의 수
lst = ['B'] * n

def cnt() :
    result = 0
    for x in range(n) :
        if lst[x] == 'A' :
            result += lst[x+1:].count("B")      #  A 이후로 B의 갯수를 세기
    return result

for i in range(n) :
    lst[i] = 'A'
    result = cnt()
    if result == k :
        print(''.join(lst))
        break
    elif result > k :       # 쌍의 수가 k보다 크면 다시 B로 돌려놓기
        lst[i] = 'B'
else :
    print(-1)