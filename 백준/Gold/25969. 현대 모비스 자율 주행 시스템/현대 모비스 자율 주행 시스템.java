import java.io.*;
import java.util.*;

public class Main {

    private static class Node {
        int x, y, k, step;
        boolean checkWayPoint;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Node(int x, int y, int k, int step, boolean checkWayPoint) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.step = step;
            this.checkWayPoint = checkWayPoint;
        }
    }

    static final int EMPTY = 0, WALL = 1, MAX = 987654321;

    static int R, C, K;
    static int[][] map;
    static boolean[][][][] visit;  // visit[x][y][k][checkWayPoint] 방문 여부 배열
    static ArrayList<Node> path = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visit = new boolean[R][C][K + 1][2];  // 방문 배열 크기 (4차원)

        // 지도 입력
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 패턴 입력 (5x5 배열)
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    path.add(new Node(i - 2, j - 2));  // 패턴에 맞는 이동 경로 저장
                }
            }
        }

        // BFS 시작
        System.out.println(bfs(new LinkedList<>(Arrays.asList(new Node(0, 0, K, 0, false)))));  // 출발점 (0, 0)부터 시작
    }

    private static int bfs(Queue<Node> q) {
        visit[0][0][K][0] = true;  // 출발점 방문처리, 중간 거점지 방문 전
        while (!q.isEmpty()) {
            Node n = q.poll();

            // 목적지에 도달하고 중간 거점지를 방문했다면, 결과 출력
            if (n.checkWayPoint && n.x == R - 1 && n.y == C - 1) {
                return n.step;
            }

            // 상하좌우로 이동
            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];

                if (isInside(nx, ny) && map[nx][ny] != WALL && !visit[nx][ny][n.k][n.checkWayPoint ? 1 : 0]) {
                    visit[nx][ny][n.k][n.checkWayPoint ? 1 : 0] = true;
                    if (n.checkWayPoint) {
                        q.add(new Node(nx, ny, n.k, n.step + 1, n.checkWayPoint));
                    } else {
                        if (map[nx][ny] == 2) {
                            q.add(new Node(nx, ny, n.k, n.step + 1, true));  // 중간 거점지 방문
                        } else {
                            q.add(new Node(nx, ny, n.k, n.step + 1, n.checkWayPoint));
                        }
                    }
                }
            }

            // 패턴을 사용할 수 있는 경우
            if (n.k > 0) {  // 패턴을 사용할 수 있으면
                for (Node p : path) {
                    int nx = n.x + p.x;
                    int ny = n.y + p.y;
                    if (isInside(nx, ny) && map[nx][ny] != WALL && !visit[nx][ny][n.k - 1][n.checkWayPoint ? 1 : 0]) {
                        visit[nx][ny][n.k - 1][n.checkWayPoint ? 1 : 0] = true;
                        if (n.checkWayPoint) {
                            q.add(new Node(nx, ny, n.k - 1, n.step + 1, n.checkWayPoint));
                        } else {
                            // 패턴을 사용한 후 중간 거점지 방문 처리
                            if (map[nx][ny] == 2) {
                                q.add(new Node(nx, ny, n.k - 1, n.step + 1, true));  // 중간 거점지 방문
                            } else {
                                q.add(new Node(nx, ny, n.k - 1, n.step + 1, n.checkWayPoint));
                            }
                        }
                    }
                }
            }
        }
        return -1;  // 도달할 수 없는 경우
    }

    private static boolean isInside(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < R && ny < C;
    }
}
