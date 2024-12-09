import java.util.Scanner;
import java.util.PriorityQueue;

public class Solution {
    static int n;   // 지도의 크기
    static int[][] arr;
    static int answer;

    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    static class Position implements Comparable<Position> {
        int x;
        int y;
        int time;

        Position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        // 우선순위 큐에서 더 작은 time을 우선적으로 꺼내기 위해 compareTo 구현
        @Override
        public int compareTo(Position o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void bfs() {
        // 우선순위 큐 사용 (시간이 작은 순으로 탐색)
        PriorityQueue<Position> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[n][n];
        pq.offer(new Position(0, 0, 0));  // 시작점 (0, 0), 시간 0
        visit[0][0] = true;

        while (!pq.isEmpty()) {
            Position cur = pq.poll();

            // 도착점에 도달한 경우
            if (cur.x == n - 1 && cur.y == n - 1) {
                answer = Math.min(answer, cur.time);
                return;  // 첫 번째로 도달한 최소 경로를 찾으면 바로 종료
            }

            // 4방향으로 이동
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + DX[d];
                int ny = cur.y + DY[d];

                if (isValid(nx, ny) && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    pq.offer(new Position(nx, ny, cur.time + arr[nx][ny]));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            answer = Integer.MAX_VALUE;
            // 지도 정보 입력받기
            arr = new int[n][n];
            for (int x = 0; x < n; x++) {
                String row = sc.next();
                for (int y = 0; y < n; y++) {
                    arr[x][y] = Integer.parseInt(String.valueOf(row.charAt(y)));
                }
            }

            bfs();  // BFS 탐색

            System.out.println("#" + tc + " " + answer);
        }
    }
}