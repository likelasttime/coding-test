import java.util.*;

class Main {
    static final int BLACK = -1;
    static final int RAINBOW = 0;
    static final int EMPTY = -2;

    static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int score = 0;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Group implements Comparable<Group> {
        List<int[]> blocks = new ArrayList<>();
        int rainbowCnt;
        int stdX, stdY; // 기준 블록 (가장 작은 행, 열)

        @Override
        public int compareTo(Group o) {
            if (this.blocks.size() != o.blocks.size())
                return o.blocks.size() - this.blocks.size();
            if (this.rainbowCnt != o.rainbowCnt)
                return o.rainbowCnt - this.rainbowCnt;
            if (this.stdX != o.stdX)
                return o.stdX - this.stdX;
            return o.stdY - this.stdY;
        }
    }

    static Group bfs(int sx, int sy) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> rainbowList = new ArrayList<>();

        visited[sx][sy] = true;
        q.add(new int[]{sx, sy});

        Group g = new Group();
        g.blocks.add(new int[]{sx, sy});
        g.stdX = sx;
        g.stdY = sy;

        int color = grid[sx][sy];

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (grid[nx][ny] == BLACK) continue;

                if (grid[nx][ny] == color || grid[nx][ny] == RAINBOW) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    g.blocks.add(new int[]{nx, ny});

                    if (grid[nx][ny] == RAINBOW) {
                        g.rainbowCnt++;
                        rainbowList.add(new int[]{nx, ny});
                    } else {
                        // 기준 블록: 가장 작은 행 → 가장 작은 열
                        if (nx < g.stdX || (nx == g.stdX && ny < g.stdY)) {
                            g.stdX = nx;
                            g.stdY = ny;
                        }
                    }
                }
            }
        }

        // 무지개 블록 방문 초기화 (다른 그룹에서 재사용 가능)
        for (int[] r : rainbowList) {
            visited[r[0]][r[1]] = false;
        }

        if (g.blocks.size() < 2) return null;
        return g;
    }

    static void gravity() {
        for (int y = 0; y < N; y++) {
            int bottom = N - 1;

            for (int x = N - 1; x >= 0; x--) {
                if (grid[x][y] == BLACK) {
                    bottom = x - 1;
                }
                else if (grid[x][y] >= 0) {
                    int temp = grid[x][y];
                    grid[x][y] = EMPTY;
                    grid[bottom][y] = temp;
                    bottom--;
                }
            }
        }
    }

    static void rotate() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                tmp[N - 1 - j][i] = grid[i][j];
        grid = tmp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        grid = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                grid[i][j] = sc.nextInt();

        while (true) {
            visited = new boolean[N][N];
            List<Group> groups = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && grid[i][j] > 0) {
                        Group g = bfs(i, j);
                        if (g != null) groups.add(g);
                    }
                }
            }

            if (groups.isEmpty()) break;

            Collections.sort(groups);
            Group best = groups.get(0);

            int size = best.blocks.size();
            score += size * size;

            for (int[] b : best.blocks) {
                grid[b[0]][b[1]] = EMPTY;
            }

            gravity();
            rotate();
            gravity();
        }

        System.out.println(score);
    }
}
