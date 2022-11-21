# 트리의 너비가 가장 넓은 레벨과 너비를 구하는 문제
# 같은 너비면 더 낮은 레벨을 출력
import sys
input = sys.stdin.readline

N = int(input())        # 노드의 개수
tree = [[0, 0] for _ in range(N + 1)]   # [왼쪽 자식 노드, 오른쪽 자식 노드]
levels = [list() for _ in range(N + 1)]     # 레벨(행)의 노드를 저장
visit = [0] * (N + 1)
column = 1

for n in range(N) :
    node, left, right = map(int, input().split())
    tree[node] = [left, right]
    visit[node] += 1
    
    if left != -1 :
        visit[left] += 1
        
    if right != -1 :
        visit[right] += 1

# 루트 노드 찾기
def find_root() :
    return visit.index(1)       # 루트 노드는 1로 표시함

def dfs(node, level) :
    global column

    left, right = tree[node]
    
    if tree[node][0] != -1 :
        dfs(left, level + 1)
        
    levels[level].append(column)
    column += 1
    
    if tree[node][1] != -1 :
        dfs(right, level + 1)

dfs(find_root(), 1)

level = 1
max_width = max(levels[1]) - min(levels[1]) + 1

for i in range(2, n + 1) :
    if not levels[i] :
        break
    
    distance = max(levels[i]) - min(levels[i]) + 1
    
    if max_width < distance :
        max_width = distance
        level = i

print(level, max_width)