from copy import deepcopy
L, C=map(int, input().split())  # 암호의 길이, 후보 문자의 종류 개수
alphabet=input().split()
alphabet.sort()
answer=list()

def check(lst) :
    vowel=0
    consonant=0
    for string in lst :
        if string in {'a', 'e', 'i', 'o', 'u'} :
            vowel += 1
        else :
            consonant += 1
    return True if vowel > 0 and consonant > 1 else False

def dfs(start, tmp) :
    if len(tmp) == L :
        if check(tmp) :    # 최소 모음 1개, 자음 2개
            copied_tmp=deepcopy(tmp)
            answer.append(copied_tmp)
        return
    for idx in range(start, C) :
        if alphabet[idx] not in tmp :
            tmp.append(alphabet[idx])
            dfs(idx+1, tmp)
            tmp.pop()

dfs(0, [])
for ans in answer :
    print(''.join(ans))