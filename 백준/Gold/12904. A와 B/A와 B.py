# S를 T로 바꾸는 게임
# 거꾸로 T를 S로 바꿀 수 있는지 확인하는 방식으로 풀었다 

# 입력
S = input()
T = input()

len_s = len(S)
copy_S = list(S)
copy_T = list(T)

while len_s < len(copy_T) :
    t = copy_T.pop()
    if t == 'B' :
        copy_T.reverse()   # 뒤집기

if copy_S == copy_T :
    print(1)
else :
    print(0)