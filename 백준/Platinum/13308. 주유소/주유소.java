import java.io.*;
import java.util.*;

/*
 * 1번부터 N번까지의 번호가 붙은 N개의 도시
 * 서로 다른 두 도시를 양방향으로 연결하는 M개의 도로
 * 도로를 이용하여 이동할 때 1km마다 1리터의 기름을 사용
 * 각 도시에는 단 하나의 주유소가 있다.
 * 도시마다 주유소의 리터당 가격은 다를 수 있다.
 * 1번 도시에서 N번 도시로 이동하는 최소의 비용을 계산
 */
public class Main {
    static int N, M;
    static int[] oil;
    static List<Edge>[] graph;
    static final long INF = Long.MAX_VALUE;

    static class Edge {
        int to, len;
        Edge(int to, int len) {
            this.to = to;
            this.len = len;
        }
    }

    static class State implements Comparable<State> {
        int city;
        int minOil;
        long cost;
        State(int city, int minOil, long cost) {
            this.city = city;
            this.minOil = minOil;
            this.cost = cost;
        }
        public int compareTo(State o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        oil = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            oil[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        System.out.println(dijkstra());
    }

    static long dijkstra() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        // dist[node][minOilPrice] 를 Map으로 관리
        Map<Integer, Long>[] dist = new HashMap[N + 1];
        for (int i = 1; i <= N; i++) dist[i] = new HashMap<>();

        pq.offer(new State(1, oil[1], 0L));
        dist[1].put(oil[1], 0L);

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            // 이미 더 짧은 경로로 왔으면 스킵
            if (dist[cur.city].getOrDefault(cur.minOil, INF) < cur.cost) continue;

            // 도착점
            if (cur.city == N) return cur.cost;

            for (Edge next : graph[cur.city]) {
                int newMinOil = Math.min(cur.minOil, oil[next.to]);
                long newCost = cur.cost + (long) next.len * cur.minOil;

                long prevCost = dist[next.to].getOrDefault(newMinOil, INF);
                if (newCost < prevCost) {
                    dist[next.to].put(newMinOil, newCost);
                    pq.offer(new State(next.to, newMinOil, newCost));
                }
            }
        }

        // 이 문제는 항상 도달 가능하므로 여기까지 오지 않음
        return -1;
    }
}
