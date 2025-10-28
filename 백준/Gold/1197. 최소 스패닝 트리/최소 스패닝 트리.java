import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node other) {
            return this.weight - other.weight; // 가중치 기준 오름차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 수
        int E = Integer.parseInt(st.nextToken()); // 간선 수

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0)); // 1번 노드에서 시작
        int ans = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            ans += cur.weight;

            for (Node next : graph.get(cur.to)) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }

        System.out.println(ans);
    }
}
