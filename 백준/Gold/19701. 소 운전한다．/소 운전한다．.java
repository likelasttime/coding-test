import java.io.*;
import java.util.*;

public class Main {
    static int v, e;
    static ArrayList<Road>[] map;
    static long[][] dist;

    static class City implements Comparable<City> {
        int idx;
        long time;
        int eat;

        City(int idx, long time, int eat) {
            this.idx = idx;
            this.time = time;
            this.eat = eat;
        }

        @Override
        public int compareTo(City o) {
            return Long.compare(this.time, o.time);
        }
    }

    static class Road {
        int to;
        long time;
        long taste;

        Road(int to, long time, long taste) {
            this.to = to;
            this.time = time;
            this.taste = taste;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        map = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            map[x].add(new Road(y, t, k));
            map[y].add(new Road(x, t, k)); // 양방향 주의
        }

        dist = new long[v + 1][2]; // 0: 안먹음, 1: 먹음
        for (int i = 0; i <= v; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        dijkstra(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= v; i++) {
            sb.append(Math.min(dist[i][0], dist[i][1])).append("\n");
        }
        System.out.print(sb);
    }

    static void dijkstra(int start) {
        PriorityQueue<City> pq = new PriorityQueue<>();
        dist[start][0] = 0;
        pq.offer(new City(start, 0, 0));

        while (!pq.isEmpty()) {
            City now = pq.poll();

            if (dist[now.idx][now.eat] < now.time) continue;

            for (Road next : map[now.idx]) {
                // 돈까스를 먹지 않고 이동
                if (dist[next.to][now.eat] > now.time + next.time) {
                    dist[next.to][now.eat] = now.time + next.time;
                    pq.offer(new City(next.to, dist[next.to][now.eat], now.eat)); // City 객체 생성
                }

                // 돈까스를 먹고 이동 (eat = 0인 경우만 가능)
                if (now.eat == 0) {
                    long reducedTime = now.time + next.time - next.taste;
                    if (dist[next.to][1] > reducedTime) {
                        dist[next.to][1] = reducedTime;
                        pq.offer(new City(next.to, reducedTime, 1)); // City 객체 생성
                    }
                }
            }
        }
    }
}
