# K원을 만드는데 필요한 동전 개수의 최솟값을 구하는 문제
# 최소 개수의 동전을 만들기 위해서 뒤에서부터 탐색함
N, K = map(int, input().split())    # 동전 종류수, 가치의 합
A = [int(input()) for n in range(N)]    # 오름차순 정렬된 동전의 가치 리스트
answer = 0
total = K

for i in range(N-1, -1, -1) :       # 뒤에서부터 탐색
    if total == 0 :
        break
    elif total >= A[i] :
        cnt = total // A[i]     # 사용할 수 있는 동전의 개수
        total -= cnt * A[i]
        answer += cnt
        
print(answer)       # K원을 만드는데 필요한 동전 개수의 최솟값