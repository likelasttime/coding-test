import java.util.*;
import java.io.*;

public class Main {
    static int n, q;
    static List<List<Node>> graph;

    static class Node {
        int to, usado;
        Node(int to, int usado) {
            this.to = to;
            this.usado = usado;
        }
    }

    public static int bfs(int k, int start) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);
        int count = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (Node neighbor : graph.get(current)) {
                if (!visited[neighbor.to] && neighbor.usado >= k) {
                    visited[neighbor.to] = true;
                    queue.offer(neighbor.to);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        // 간선 입력
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph.get(p).add(new Node(q, r));
            graph.get(q).add(new Node(p, r));
        }

        // 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(bfs(k, v)).append("\n");
        }

        System.out.print(sb);
    }
}
