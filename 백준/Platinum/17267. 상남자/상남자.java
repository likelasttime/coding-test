import java.util.*;
import java.io.*;

public class Main {
    static int N, M, L, R;
    static char[][] map;
    static boolean[][] visited;

    static class Node {
        int x, y, l, r;
        Node(int x, int y, int l, int r) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.r = r;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        int sx = 0, sy = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '2') {
                    sx = i;
                    sy = j;
                }
            }
        }

        System.out.println(bfs(sx, sy));
    }

    static int bfs(int sx, int sy) {
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(sx, sy, L, R));
        visited[sx][sy] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 위로
            if (cur.x - 1 >= 0 && map[cur.x - 1][cur.y] == '0' && !visited[cur.x - 1][cur.y]) {
                visited[cur.x - 1][cur.y] = true;
                q.addFirst(new Node(cur.x - 1, cur.y, cur.l, cur.r)); // 상하는 제약 없음, 우선 처리
                cnt++;
            }
            // 아래로
            if (cur.x + 1 < N && map[cur.x + 1][cur.y] == '0' && !visited[cur.x + 1][cur.y]) {
                visited[cur.x + 1][cur.y] = true;
                q.addFirst(new Node(cur.x + 1, cur.y, cur.l, cur.r));
                cnt++;
            }

            // 왼쪽으로 확장
            if (cur.l > 0) {
                int ny = cur.y - 1;
                if (ny >= 0 && map[cur.x][ny] == '0' && !visited[cur.x][ny]) {
                    visited[cur.x][ny] = true;
                    q.addLast(new Node(cur.x, ny, cur.l - 1, cur.r));
                    cnt++;
                }
            }

            // 오른쪽으로 확장
            if (cur.r > 0) {
                int ny = cur.y + 1;
                if (ny < M && map[cur.x][ny] == '0' && !visited[cur.x][ny]) {
                    visited[cur.x][ny] = true;
                    q.addLast(new Node(cur.x, ny, cur.l, cur.r - 1));
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
