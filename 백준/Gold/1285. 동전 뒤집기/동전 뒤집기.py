# 뒷면이 위를 향하여 놓일 수 있는 동전의 최소 개수를 구하는 문제
# 행 또는 열을 뒤집는다
# 비트마스크 사용
# 미리 모두 뒤집은 배열을 만들어둔다
import sys
input = sys.stdin.readline

N = int(input())
answer = 20 * 20
coins = [list(input().rstrip()) for n in range(N)]
reversed_coins = [c[:] for c in coins]

# 모든 동전을 뒤집었다
for i in range(N) :
    for j in range(N) :
        if reversed_coins[i][j] == 'H' :
            reversed_coins[i][j] = 'T'
        else :
            reversed_coins[i][j] = 'H'

# 비트마스크를 이용해 모든 경우의 수를 탐색(2**N가지 방법)
for bit in range(1 << N) :
    tmp = []
    for i in range(N) :
        if bit & (1 << i) :     # i가 포함됨
            tmp.append(reversed_coins[i][:])
        else :
            tmp.append(coins[i][:])

    cnt_back = 0
    # 열 탐색
    for y in range(N) :
        cnt = 0
        for x in range(N) :
            if tmp[x][y] == 'T' :
                cnt += 1
        cnt_back += min(cnt, N - cnt)		
    answer = min(answer, cnt_back)

print(answer)