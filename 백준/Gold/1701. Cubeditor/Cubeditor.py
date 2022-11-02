# 두 번 이상 나오는 부분 문자열 중에서 가장 긴 길이를 찾는 문제
# 두 문자가 같으면 table 리스트에 길이를 넣는다
input_string = input()
answer = 0

def find_max_len(string) :
    s = 0
    len_string = len(string)
    table = [0] * len(string)

    for e in range(1, len_string) :
        while s > 0 and string[s] != string[e] :
            s = table[s - 1]

        if string[s] == string[e] :
            s += 1
            table[e] = s   # 인덱스 e까지 두 번 이상 나오는 부분 문자열의 최대 길이

    return table

for i in range(len(input_string)) :
    answer = max(answer, max(find_max_len(input_string[i:])))

print(answer)