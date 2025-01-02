import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    static int n;
    static int m;
    static int[][] arr;
    static int white;
    static int black;

    // 상, 오른족 대각선 위, 우, 오른쪽 대각선 아래, 하, 왼쪽 대각선 아래, 좌, 왼쪽 대각선 위
    final static int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    final static int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 유효한 좌표인지 확인하는 함수
    public static boolean isValid(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    // 돌을 뒤집는 함수
    public static void put(Queue<Position> que, int color) {
        while(!que.isEmpty()) {
            Position cur = que.poll();
            arr[cur.x][cur.y] = color;
            if(color == 1) {        // 흑돌이면
                black++;
                white--;
            } else {                // 백돌이면
                black--;
                white++;
            }
        }
    }

    // 돌을 놓고 뒤집는 함수
    public static void play(int x, int y, int color) {
        for(int d = 0; d < 8; d++) {
            Queue<Position> que = new LinkedList<>();
            int nx = x + DX[d];
            int ny = y + DY[d];
            while (isValid(nx, ny)) {
                if(arr[nx][ny] == color) {      // 같은 돌이라면
                    put(que, color);
                    break;
                } else if(arr[nx][ny] != 0) {   // 빈 공간이 아니면
                    que.add(new Position(nx, ny));
                } else {        // 빈 공간이라면
                    break;
                }
                nx += DX[d];
                ny += DY[d];
            }
        }
        arr[x][y] = color;
        if(color == 1) {        // 흑돌이라면
            black++;
        } else {        // 백돌이라면
            white++;
        }
    }

    // 초기 돌 배치
    public static void init() {
        // 백돌 두기
        int half = n / 2;
        arr[half + 1][half + 1] = 2;
        arr[half][half] = 2;
        white += 2;

        // 흑돌 두기
        arr[half][half + 1] = 1;
        arr[half + 1][half] = 1;
        black += 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 전체 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());       // 보드의 한 변의 길이
            m = Integer.parseInt(st.nextToken());       // 플레이어가 돌을 놓는 횟수
            arr = new int[n + 1][n + 1];  // 배열 크기를 1-based로 설정
            black = 0;
            white = 0;

            init();     // 정가운데에 백돌, 흑돌 두기

            // 돌을 놓을 위치와 돌의 색 입력 받기
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int color = Integer.parseInt(st.nextToken());
                play(x, y, color);
            }
            System.out.println("#" + tc + " " + black + " " + white);
        }
    }
}