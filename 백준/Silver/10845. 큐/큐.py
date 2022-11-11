import sys
from collections import deque
input = sys.stdin.readline

N = int(input())        # 명령의 수
cmd = [input().split() for n in range(N)]
que = deque()

for c in cmd :
    command = c[0]
    if command == "push" :
        que.append(int(c[1]))
    elif command == "pop" :
        if que :
            print(que.popleft())
        else :
            print(-1)
    elif command == "size" :
        print(len(que))
    elif command == "empty" :
        if not que :
            print(1)        # 큐가 비었다
        else :
            print(0)        # 큐가 비어있지 않다
    elif command == "front" :
        if que :
            print(que[0])
        else :
            print(-1)
    else :      # 큐에 원소가 있을 때 가장 뒤에 있는 정수를 출력
        if que :
            print(que[-1])
        else :
            print(-1)