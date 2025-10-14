import java.util.*;

public class Main {
    static int R, C, N;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String s = sc.next();
            for (int j = 0; j < C; j++) map[i][j] = s.charAt(j);
        }

        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int h = sc.nextInt();
            int row = R - h; // 높이 1은 바닥
            if (i % 2 == 0) throwFromLeft(row);
            else throwFromRight(row);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) sb.append(map[i][j]);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void throwFromLeft(int row) {
        for (int j = 0; j < C; j++) {
            if (map[row][j] == 'x') {
                map[row][j] = '.';
                checkCluster(row, j);
                return;
            }
        }
    }

    static void throwFromRight(int row) {
        for (int j = C - 1; j >= 0; j--) {
            if (map[row][j] == 'x') {
                map[row][j] = '.';
                checkCluster(row, j);
                return;
            }
        }
    }

    // 파괴된 미네랄 주변에서 공중에 뜬 클러스터 찾아 낙하
    static void checkCluster(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny) || map[nx][ny] == '.') continue;

            boolean[][] visit = new boolean[R][C];
            List<int[]> cluster = bfs(nx, ny, visit);
            if (cluster == null) continue; // 바닥에 닿아있으면 스킵

            drop(cluster, visit);
            return; // 한 번만 떨어뜨리면 됨
        }
    }

    static List<int[]> bfs(int sx, int sy, boolean[][] visit) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> cluster = new ArrayList<>();
        q.offer(new int[]{sx, sy});
        visit[sx][sy] = true;
        boolean onGround = false;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cluster.add(cur);
            if (cur[0] == R - 1) onGround = true;

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!inRange(nx, ny) || visit[nx][ny] || map[nx][ny] == '.') continue;
                visit[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        return onGround ? null : cluster;
    }

    static void drop(List<int[]> cluster, boolean[][] visit) {
        for (int[] c : cluster) map[c[0]][c[1]] = '.'; // 일단 다 지움

        int dropDist = R;
        for (int[] c : cluster) {
            int r = c[0] + 1;
            int ccol = c[1];
            int dist = 0;
            while (r < R && map[r][ccol] == '.') {
                r++;
                dist++;
            }
            // 다른 클러스터에 닿으면 그 위까지
            dropDist = Math.min(dropDist, dist);
        }

        for (int[] c : cluster)
            map[c[0] + dropDist][c[1]] = 'x';
    }

    static boolean inRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
