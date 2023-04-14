import sys
from collections import defaultdict
sys.setrecursionlimit(1000001)

def solution(n, lighthouse):
    dic = defaultdict(list)
    for u, v in lighthouse:
        dic[u].append(v)
        dic[v].append(u)
        
    visit = [0] * (n + 1)
        
    def dfs(cur) :
        nonlocal visit
        if not dic[cur] :
            return 1, 0
        on, off = 1, 0
        visit[cur] = 1
        for val in dic[cur] :
            if visit[val] : continue
            on1, off1 = dfs(val)
            on += min(on1, off1)
            off += on1
        return on, off
        
    on, off = dfs(1)
    
    return min(on, off)