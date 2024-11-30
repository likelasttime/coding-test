import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
    static int[] tree;
    static List<Integer>[] reverseTree;
    static List<Integer> node1Parents;
    static List<Integer> node2Parents;
    static int[] subtreeCnt;
    static int answerSubtreeCnt;
    static int v;

    // 서브트리 크기 계산 (재귀 방식)
    public static int calSubtreeCnt(int cur, boolean[] visit) {
        if (visit[cur]) return 0; // 이미 방문한 노드는 계산하지 않음
        visit[cur] = true;
        int cnt = 1; // 현재 노드를 포함하여 시작

        // 자식 노드들에 대해 서브트리 크기를 더함
        for (int child : reverseTree[cur]) {
            if (!visit[child]) {
                cnt += calSubtreeCnt(child, visit);
            }
        }

        // 계산된 서브트리 크기를 subtreeCnt 배열에 저장
        subtreeCnt[cur] = cnt;
        return cnt;
    }

    // 공통 조상 찾기 (dfs)
    public static void dfs(int cur, List<Integer> parents) {
        if (cur == 1) {  // 루트 노드에 도달하면 종료
            return;
        }
        parents.add(tree[cur]);
        dfs(tree[cur], parents);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());  // 테스트케이스 수

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());  // 정점의 개수
            int e = Integer.parseInt(st.nextToken());  // 간선의 개수
            int node1 = Integer.parseInt(st.nextToken());  // 첫 번째 노드
            int node2 = Integer.parseInt(st.nextToken());  // 두 번째 노드
            subtreeCnt = new int[v + 1];
            int answerP = 1;
            tree = new int[v + 1];  // 자식 -> 부모
            reverseTree = new ArrayList[v + 1];  // 부모 -> 자식
            node1Parents = new ArrayList<>();
            node2Parents = new ArrayList<>();

            // reverseTree 초기화
            for (int i = 1; i <= v; i++) {
                reverseTree[i] = new ArrayList<>();
            }

            // 간선 입력 받기
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < e * 2; i += 2) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());
                tree[child] = parent;
                reverseTree[parent].add(child);
            }

            // 공통 조상 찾기
            dfs(node1, node1Parents);
            dfs(node2, node2Parents);

            // 공통 조상 찾기
            for (int p : node1Parents) {
                if (node2Parents.contains(p)) {
                    answerP = p;
                    break;
                }
            }

            // 서브트리 크기 계산
            boolean[] visit = new boolean[v + 1];
            calSubtreeCnt(answerP, visit);
            answerSubtreeCnt = subtreeCnt[answerP];

            // 결과 출력
            System.out.println("#" + tc + " " + answerP + " " + answerSubtreeCnt);
        }
    }
}