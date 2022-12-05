# 이진 트리의 인오더와 포스트오더가 주어졌을 때 프리오더를 구하는 문제
import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

def dfs(in_start, in_end, post_start, post_end) :
    if in_start > in_end or post_start > post_end :
        return

    root = post_order[post_end]
    print(root, end=" ")

    left = position[root] - in_start
    right = in_end - position[root]

    dfs(in_start, in_start + left - 1, post_start, post_start + left - 1)
    dfs(in_end - right + 1, in_end, post_end - right, post_end - 1)

n = int(input())    # 정점의 수(1 <= n <= 100,000)
inorder = list(map(int, input().split()))
post_order = list(map(int, input().split()))

position = [0] * (n+1)
for idx in range(n) :
    position[inorder[idx]] = idx

dfs(0, n-1, 0, n-1)