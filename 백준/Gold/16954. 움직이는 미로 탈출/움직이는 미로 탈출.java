import java.util.*;

class Main {
    static class Position {
        int x, y;
        Position(int x, int y) { this.x = x; this.y = y; }
    }

    final static int N = 8;
    final static char WALL = '#';
    // 9방향 (대각선 포함) + 제자리
    final static int[] DX = {-1, 1, 0, 0, -1, -1, 1, 1, 0};
    final static int[] DY = {0, 0, -1, 1, -1, 1, -1, 1, 0};

    static char[][] arr;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static int bfs() {
        Queue<Position> que = new LinkedList<>();
        que.offer(new Position(N - 1, 0)); // 시작

        while (!que.isEmpty()) {
            int size = que.size();
            // 매 시간대마다 방문 배열 초기화 (같은 시간대에 중복 삽입 방지)
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < size; i++) {
                Position cur = que.poll();
                // 현재 위치에 벽이 내려왔으면 이 상태는 무시
                if (arr[cur.x][cur.y] == WALL) continue;
                // 목표 도달
                if (cur.x == 0 && cur.y == N - 1) return 1;

                for (int d = 0; d < DX.length; d++) {
                    int nx = cur.x + DX[d];
                    int ny = cur.y + DY[d];

                    if (!inRange(nx, ny)) continue;
                    // 현재 상태에서 이미 벽이면 못감
                    if (arr[nx][ny] == WALL) continue;
                    // 다음 초(벽이 내려온 뒤)에 벽이 떨어지지 않는지도 확인
                    // 즉, 현재 (nx-1, ny)에 벽이 있으면 다음 초에 (nx,ny)에 벽이 됨 -> 위험
                    if (nx - 1 >= 0 && arr[nx - 1][ny] == WALL) continue;

                    if (visited[nx][ny]) continue;
                    visited[nx][ny] = true;
                    que.offer(new Position(nx, ny));
                }
            }
            // 벽 이동 (1초 경과)
            moveWall();
        }
        return 0;
    }

    public static void moveWall() {
        char[][] copied = new char[N][N];
        for (int x = 0; x < N; x++) Arrays.fill(copied[x], '.');

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (arr[x][y] == WALL) {
                    int nx = x + 1;
                    if (nx < N) copied[nx][y] = WALL;
                    // nx == N 이면 벽이 사라짐
                }
            }
        }
        arr = copied;
        // 디버그 출력이 필요하면 사용, 제출시 주석처리 권장
        // printArr();
    }

    public static void printArr() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                System.out.print(arr[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new char[N][N];
        for (int x = 0; x < N; x++) {
            String row = sc.next();
            for (int y = 0; y < N; y++) arr[x][y] = row.charAt(y);
        }
        System.out.print(bfs());
    }
}
