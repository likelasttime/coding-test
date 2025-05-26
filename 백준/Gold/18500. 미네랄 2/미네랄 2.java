import java.io.*;
import java.util.*;

public class Main {
    final static int[] dx = {1, -1, 0, 0};
    final static int[] dy = {0, 0, 1, -1};
    
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int turn = 0; turn < N; turn++) {
            int h = Integer.parseInt(st.nextToken());
            int row = R - h;
            boolean removed = false;

            if (turn % 2 == 0) {
                for (int j = 0; j < C; j++) {
                    if (map[row][j] == 'x') {
                        map[row][j] = '.';
                        removed = true;
                        break;
                    }
                }
            } else {
                for (int j = C - 1; j >= 0; j--) {
                    if (map[row][j] == 'x') {
                        map[row][j] = '.';
                        removed = true;
                        break;
                    }
                }
            }

            if (!removed) continue;

            visited = new boolean[R][C];
            for (int j = 0; j < C; j++) {
                if (map[R - 1][j] == 'x' && !visited[R - 1][j]) {
                    bfs(R - 1, j);
                }
            }

            boolean moved = false;
            for (int i = 0; i < R && !moved; i++) {
                for (int j = 0; j < C && !moved; j++) {
                    if (map[i][j] == 'x' && !visited[i][j]) {
                        List<int[]> cluster = getCluster(i, j);
                        dropCluster(cluster);
                        moved = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char[] row : map) {
            sb.append(row).append('\n');
        }
        System.out.print(sb);
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                    if (!visited[nx][ny] && map[nx][ny] == 'x') {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    static List<int[]> getCluster(int x, int y) {
        List<int[]> cluster = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        boolean[][] tempVisited = new boolean[R][C];
        tempVisited[x][y] = true;
        q.offer(new int[]{x, y});
        cluster.add(new int[]{x, y});
        map[x][y] = '.';

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                    if (!tempVisited[nx][ny] && !visited[nx][ny] && map[nx][ny] == 'x') {
                        tempVisited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        cluster.add(new int[]{nx, ny});
                        map[nx][ny] = '.';
                    }
                }
            }
        }

        return cluster;
    }

    static void dropCluster(List<int[]> cluster) {
        int drop = 0;
        boolean canDrop = true;

        while (canDrop) {
            for (int[] pos : cluster) {
                int nx = pos[0] + drop + 1;
                int ny = pos[1];
                if (nx >= R || map[nx][ny] == 'x') {
                    canDrop = false;
                    break;
                }
            }
            if (canDrop) drop++;
        }

        for (int[] pos : cluster) {
            map[pos[0] + drop][pos[1]] = 'x';
        }
    }
}
