N = list(input())       # 양수
N.sort(reverse = True)      # 큰 수부터 사용하기 위해 내림차순 정렬
size = len(N)
total = 0       # N에 있는 수의 총합

for n in N :
    total += int(n)

if total % 3 != 0 or '0' not in set(N) :  
    print(-1)
else :
    print(''.join(N))