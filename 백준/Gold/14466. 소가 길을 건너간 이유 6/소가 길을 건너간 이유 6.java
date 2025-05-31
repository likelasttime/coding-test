import java.util.*;
import java.io.*;

public class Main {
    // 상, 우, 하, 좌
    final static int[] DX = {-1, 0, 1, 0};
    final static int[] DY = {0, 1, 0, -1};

    static int n, k, r;
    static boolean[][][] matrix; // [x][y][방향]: 해당 방향으로 길이 막혀 있음
    static Position[] cows;
    static int answer = 0;

    static class Position {
        int x, y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isValid(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= n;
    }

    // BFS 결과: 해당 소가 도달 가능한 위치 visited[x][y]
    public static boolean[][] bfs(int sx, int sy) {
        boolean[][] visited = new boolean[n + 1][n + 1];
        Queue<Position> queue = new LinkedList<>();
        visited[sx][sy] = true;
        queue.offer(new Position(sx, sy));

        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + DX[d];
                int ny = cur.y + DY[d];
                if (!isValid(nx, ny)) continue;
                if (matrix[cur.x][cur.y][d]) continue; // 길이 막혀 있음
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Position(nx, ny));
            }
        }
        return visited;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        matrix = new boolean[n + 1][n + 1][4];

        // 길 정보 입력
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            for (int d = 0; d < 4; d++) {
                int nx = r1 + DX[d];
                int ny = c1 + DY[d];
                if (nx == r2 && ny == c2) {
                    matrix[r1][c1][d] = true;
                    matrix[r2][c2][(d + 2) % 4] = true;
                    break;
                }
            }
        }

        // 소 위치 입력
        cows = new Position[k + 1];
        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 모든 소 쌍에 대해 접근 가능한지 확인
        for (int i = 1; i <= k; i++) {
            boolean[][] visited = bfs(cows[i].x, cows[i].y);
            for (int j = i + 1; j <= k; j++) {
                if (!visited[cows[j].x][cows[j].y]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
