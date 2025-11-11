import java.io.*;
import java.util.*;

/*
 * R * C 격자판
 * 낚시왕은 처음에 1번 열의 한 칸 왼쪽에 있다.
 * 낚시왕은 가장 오른쪽 열의 오른쪽 칸에 이동하면 멈춘다.
 * 1초 동안 아래의 일이 순서대로 일어난다.
 * 	1) 오른쪽으로 1칸 이동
 * 	2) 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
 * 		상어를 잡으면 격자판에서 상어가 사라진다.
 * 	3) 상어가 이동한다.
 * 		격자판을 넘어가면, 방향을 반대로 바꿔서 속력을 유지한채로 이동한다.
 * 		이동을 마친 후 한 칸에 상어가 2마리 이상 있으면
 * 			크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
 * 
 * 낚시왕이 잡은 상어 크기의 합을 구한다.
 */
public class Main {
    final static int[] DX = {-1, 1, 0, 0}; // 상, 하, 우, 좌
    final static int[] DY = {0, 0, 1, -1};
    final static boolean DEBUG = false;

    static int R;
    static int C;
    static int M;
    static int answer;
    static Shark[][] board;

    static class Shark {
        int s; // 속력
        int d; // 방향 0:상 1:하 2:우 3:좌
        int z; // 크기

        Shark(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return "속력=" + s + " 방향=" + d + " 크기=" + z;
        }
    }

    public static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    // 안전하게 한 칸씩 이동(또는 축소된 속력만큼 반복)하는 방법
    public static void moveShark(Shark[][] tmp, int r, int c) {
        Shark cur = board[r][c];
        int s = cur.s;
        int d = cur.d;
        int z = cur.z;

        int nr = r;
        int nc = c;

        // 주기 계산: R,C >= 2 라는 조건 (문제 제약)
        if (d == 0 || d == 1) { // 상하
            int cycle = 2 * (R - 1);
            if (cycle != 0) s %= cycle;
        } else { // 좌우
            int cycle = 2 * (C - 1);
            if (cycle != 0) s %= cycle;
        }

        // 한 칸씩 안전하게 이동 (s는 이미 줄여져 있음)
        for (int i = 0; i < s; i++) {
            int tr = nr + DX[d];
            int tc = nc + DY[d];
            if (tr < 0 || tr >= R || tc < 0 || tc >= C) {
                // 방향 반전
                if (d == 0) d = 1;
                else if (d == 1) d = 0;
                else if (d == 2) d = 3;
                else if (d == 3) d = 2;
                tr = nr + DX[d];
                tc = nc + DY[d];
            }
            nr = tr;
            nc = tc;
        }

        // tmp에 넣기 (크기 비교해서 큰 것만 남김)
        if (tmp[nr][nc] == null || tmp[nr][nc].z < z) {
            tmp[nr][nc] = new Shark(cur.s, d, z);
        }
    }

    public static void moveAllShark() {
        Shark[][] tmp = new Shark[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (board[r][c] == null) continue;
                moveShark(tmp, r, c);
            }
        }
        board = tmp;
    }

    // c열에서 가장 위(행이 작은) 상어 잡기
    public static void fishing(int c) {
        for (int r = 0; r < R; r++) {
            if (board[r][c] != null) {
                answer += board[r][c].z;
                board[r][c] = null;
                break;
            }
        }
    }

    public static void printBoard() {
        if (!DEBUG) return;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (board[r][c] != null) {
                    System.out.println(board[r][c] + " " + r + " " + c);
                }
            }
        }
        System.out.println("----");
    }

    public static void simulate() {
        for (int c = 0; c < C; c++) {
            // 1) 그 열에서 잡기
            fishing(c);
            // 2) 상어 이동
            moveAllShark();
            printBoard();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1; // 0..3
            int z = Integer.parseInt(st.nextToken());
            board[r][c] = new Shark(s, d, z);
        }

        simulate();
        System.out.println(answer);
    }
}
