n, m = map(int, input().split())        # 세로, 가로

if n == 1 :
    print(1)
elif n == 2 :
    print(min(4, (m-1)//2+1))
elif m <= 6 :
    print(min(4, m))
else :      # n > 2 and m > 6
    print(m-2)   