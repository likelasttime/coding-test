import java.io.*;
import java.util.*;

public class Main {

    static class Horse {
        int x, y, d;

        Horse(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int N, K;
    static int[][] color;
    static Deque<Integer>[][] map;
    static Horse[] horse;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static final int WHITE = 0;
    static final int RED = 1;
    static final int BLUE = 2;

    static boolean inRange(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }

    static int reverseDir(int d) {
        if (d == 0) return 1;
        if (d == 1) return 0;
        if (d == 2) return 3;
        return 2;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N + 1][N + 1];
        map = new ArrayDeque[N + 1][N + 1];
        horse = new Horse[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            horse[i] = new Horse(x, y, d);
            map[x][y].add(i);
        }

        for (int turn = 1; turn <= 1000; turn++) {

            for (int id = 1; id <= K; id++) {
                int x = horse[id].x;
                int y = horse[id].y;
                int d = horse[id].d;

                int nx = x + dx[d];
                int ny = y + dy[d];

                // BLUE 또는 범위 밖인 경우 → 방향 반전
                if (!inRange(nx, ny) || color[nx][ny] == BLUE) {
                    d = reverseDir(d);
                    horse[id].d = d;
                    nx = x + dx[d];
                    ny = y + dy[d];

                    if (!inRange(nx, ny) || color[nx][ny] == BLUE) {
                        continue; // 이동 불가
                    }
                }

                // 현재 칸에서 id 및 위 말들 추출
                List<Integer> stack = new ArrayList<>();
                while (true) {
                    int top = map[x][y].pollLast();
                    stack.add(top);
                    if (top == id) break;
                }

                // WHITE → 원래 순서 유지 = reverse 필요
                // RED → pollLast로 인해 이미 뒤집혀 있으므로 그대로 둬야 함
                if (color[nx][ny] == WHITE) {
                    Collections.reverse(stack);
                }

                // 이동
                for (int h : stack) {
                    map[nx][ny].add(h);
                    horse[h].x = nx;
                    horse[h].y = ny;
                }

                // 종료 조건
                if (map[nx][ny].size() >= 4) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }
}
