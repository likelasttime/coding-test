import java.util.*;
import java.io.*;

class Main {
    static class Node implements Comparable<Node> {
        int id;
        long dist;
        Node(int id, long dist) {
            this.id = id;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static class Road {
        int to, price;
        Road(int to, int price) {
            this.to = to;
            this.price = price;
        }
    }

    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static List<Road>[] road;
    static boolean[][] impossible;
    static List<Integer>[] prev;
    static long[] dist;

    static void init() {
        road = new ArrayList[N];
        for (int i = 0; i < N; i++) road[i] = new ArrayList<>();
    }

    // 다익스트라
    static void dijkstra(int S) {
        dist = new long[N];
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(S, 0));
        dist[S] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.dist > dist[cur.id]) continue;

            for (Road next : road[cur.id]) {
                if (impossible[cur.id][next.to]) continue; // 제거된 간선 건너뛰기

                long newDist = dist[cur.id] + next.price;
                if (newDist < dist[next.to]) {
                    dist[next.to] = newDist;
                    pq.offer(new Node(next.to, newDist));

                    prev[next.to].clear();
                    prev[next.to].add(cur.id);
                } else if (newDist == dist[next.to]) {
                    prev[next.to].add(cur.id);
                }
            }
        }
    }

    // 최단 경로에 포함된 간선들 제거
    static void removeShortestEdges(int S, int E) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(E);

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == S) continue;
            for (int p : prev[now]) {
                if (!impossible[p][now]) {
                    impossible[p][now] = true;
                    q.offer(p);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;

            init();
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int U = Integer.parseInt(st.nextToken());
                int V = Integer.parseInt(st.nextToken());
                int P = Integer.parseInt(st.nextToken());
                road[U].add(new Road(V, P));
            }

            impossible = new boolean[N][N];
            prev = new ArrayList[N];
            for (int i = 0; i < N; i++) prev[i] = new ArrayList<>();

            // 1️⃣ 최단 경로 구하기
            dijkstra(S);

            // 2️⃣ 최단 경로 간선 제거
            removeShortestEdges(S, E);

            // 3️⃣ 제거된 상태로 다시 다익스트라
            prev = new ArrayList[N];
            for (int i = 0; i < N; i++) prev[i] = new ArrayList<>();
            dijkstra(S);

            long ans = dist[E];
            sb.append(ans == INF ? -1 : ans).append("\n");
        }

        System.out.print(sb.toString());
    }
}
