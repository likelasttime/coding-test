import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[][][] box;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    
    static class Point {
        int x, y, z;
        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이

        box = new int[H][N][M];
        Queue<Point> q = new LinkedList<>();

        // 입력 받기
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    box[h][n][m] = Integer.parseInt(st.nextToken());
                    if (box[h][n][m] == 1) {
                        q.offer(new Point(m, n, h)); // 익은 토마토 큐에 넣기
                    }
                }
            }
        }

        // BFS 진행
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 6; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nz = cur.z + dz[i];

                if (nx >= 0 && ny >= 0 && nz >= 0 &&
                    nx < M && ny < N && nz < H) {
                    if (box[nz][ny][nx] == 0) {
                        box[nz][ny][nx] = box[cur.z][cur.y][cur.x] + 1;
                        q.offer(new Point(nx, ny, nz));
                    }
                }
            }
        }

        // 결과 확인
        int result = Integer.MIN_VALUE;
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (box[h][n][m] == 0) { // 안 익은 토마토 발견
                        System.out.println(-1);
                        return;
                    }
                    result = Math.max(result, box[h][n][m]);
                }
            }
        }

        // 처음 익은 토마토가 1이므로 -1 해줌
        System.out.println(result - 1);
    }
}
