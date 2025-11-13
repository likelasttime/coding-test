import java.io.*;
import java.util.*;

public class Main {
    // 트리 노드 관련 정보 클래스
    static class Node {
        int next, width;
        public Node(int next, int width) {
            this.next = next;
            this.width = width;
        }
    }

    static int N, M, maxDepth = -1;
    static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    static int[] depth, width;
    static int[][] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // 최대 깊이 구하는 함수 호출
        getMaxDepth();

        // 부모 노드 배열 초기화
        parent = new int[N + 1][maxDepth + 1];
        width = new int[N + 1];
        depth = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        // 트리 간선 정보 입력
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree.get(a).add(new Node(b, w));
            tree.get(b).add(new Node(a, w));
        }

        // 트리 정보 설정 (DFS)
        setTree(1, 0, 0, 0);

        // 부모 배열 채우기 (이진 상승법)
        fillParent();

        M = Integer.parseInt(br.readLine());

        // M개의 쿼리 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 두 노드의 거리 계산
            int result = width[a] + width[b] - 2 * width[LCA(a, b)];
            bw.write(result + "\n");
        }

        // 결과 출력
        bw.flush();
        bw.close();
        br.close();
    }

    // 최대 깊이 계산 (log N)
    static void getMaxDepth() {
        maxDepth = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
    }

    // 트리의 깊이와 루트에서의 거리 저장 (DFS)
    static void setTree(int current, int parentNode, int currentDepth, int currentWidth) {
        depth[current] = currentDepth;
        width[current] = currentWidth;

        for (Node neighbor : tree.get(current)) {
            if (neighbor.next == parentNode) continue;

            parent[neighbor.next][0] = current;
            setTree(neighbor.next, current, currentDepth + 1, currentWidth + neighbor.width);
        }
    }

    // 부모 노드 배열 채우기 (이진 상승법)
    static void fillParent() {
        for (int i = 1; i <= maxDepth; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    // 최소 공통 조상 (LCA) 찾기
    static int LCA(int a, int b) {
        // a가 b보다 깊이가 더 깊도록 맞추기
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        // 깊이 맞추기
        for (int i = maxDepth - 1; i >= 0; i--) {
            if (depth[a] - (1 << i) >= depth[b]) {
                a = parent[a][i];
            }
        }

        // a와 b가 같으면 바로 리턴
        if (a == b) return a;

        // a와 b가 다른 조상이라면, 동시에 부모로 올라가기
        for (int i = maxDepth - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        // 최소 공통 조상 리턴
        return parent[a][0];
    }
}
