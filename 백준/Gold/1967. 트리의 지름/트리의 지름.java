import java.util.*;
import java.io.*;

public class Main {
    static int[] answer;
    static int n;        // 1 <= 노드의 개수 n <= 10,000
    static List<List<Node>> graph;
    static int node;
    static int totalPrice;      // 가중치 합
    static boolean[] visit;

    static class Node {
        int child;      // 자식 번호
        int price;      // 가중치
        Node(int child, int price) {
            this.child = child;
            this.price = price;
        }
    }

    public static void dfs(int cur, int price) {
        if(totalPrice < price) {
            totalPrice = price;
            node = cur;
        }

        visit[cur] = true;

        for(Node node : graph.get(cur)) {
            if(!visit[node.child]) {
                dfs(node.child, price + node.price);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        visit = new boolean[n + 1];
        for (int i=0; i<=n; i++) graph.add(new ArrayList<>());

        // 간선 입력
        for (int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            // 양방향으로 저장하기
            graph.get(parent).add(new Node(child, value));
            graph.get(child).add(new Node(parent, value));
        }

        // 임의 정점 1번 노드에서 시작해서 가장 가중치의 합이 큰 노드 찾기
        dfs(1, 0);
        totalPrice = 0;
        visit = new boolean[n + 1];
        dfs(node, 0);
        System.out.println(totalPrice);
    }
}
