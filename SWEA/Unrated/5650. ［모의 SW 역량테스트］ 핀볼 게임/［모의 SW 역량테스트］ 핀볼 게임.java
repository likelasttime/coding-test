import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

class Solution {
    final static int MAX_WORMHOLE_CNT = 5;
    final static int[][] block = {
            {0, 1, 2, 3},
            {1, 3, 0, 2},
            {1, 2, 3, 0},
            {2, 0, 3, 1},
            {3, 0, 1, 2},
            {1, 0, 3, 2}
    };           // 5가지의 블록에 대해 방향별로 정리
    final static int[] DX = {0, 0, 1, -1};
    final static int[] DY = {1, -1, 0, 0};

    static int n;       // 5 <= 한 변의 길이 <= 100
    static int[][] board;
    static int[][] wormhole;        // 0열과 2열: 웜홀의 행, 1열과 3열: 웜홀의 열

    public static int[] moveWormhole(int num, int x, int y) {
        int x1 = wormhole[num - 6][0];
        int y1 = wormhole[num - 6][1];
        int x2 = wormhole[num - 6][2];
        int y2 = wormhole[num - 6][3];
        if(x1 == x && y1 == y) {
            return new int[]{x2, y2};
        }
        return new int[]{x1, y1};
    }

    /*
        x, y: 핀볼의 시작 좌표
        d: 처음에 핀볼이 움직일 방향
     */
    public static int play(int x, int y, int d) {
        int curX = x;
        int curY = y;
        int curD = d;
        int score = 0;      // 획득한 점수

        while(true) {
            curX += DX[curD];
            curY += DY[curD];

            if(board[curX][curY] == -1 || (x == curX && y == curY)) {       // 블랙홀 또는 시작 위치로 돌아오면
                break;
            }

            if(board[curX][curY] != 0) {
                if (board[curX][curY] < 6) {       // 블록에 부딪혔을 때
                    curD = block[board[curX][curY]][curD];       // 블록을 만났을 때 다음에 움직일 방향 구하기
                    score += 1;
                } else {      // 웜홀 이동
                    int[] newPos = moveWormhole(board[curX][curY], curX, curY);
                    curX = newPos[0];
                    curY = newPos[1];
                }
            }
        }
        return score;
    }

    public static void generateWall() {
        for(int i=0; i<=n+1; i++) {
            board[i][0] = board[0][i] = board[i][n + 1] = board[n + 1][i] = 5;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 총 테스트 케이스의 개수

        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int answer = 0;     // 최대 점수 값

            // 웜홀 배열 초기화
            wormhole = new int[MAX_WORMHOLE_CNT][4];
            for(int i=0; i<MAX_WORMHOLE_CNT; i++) {
                Arrays.fill(wormhole[i], -1);
            }

            // 핀볼 게임판 입력받기
            board = new int[n + 2][n + 2];
            for(int x=1; x<=n; x++) {
                st = new StringTokenizer(br.readLine());
                for(int y=1; y<=n; y++) {
                    board[x][y] = Integer.parseInt(st.nextToken());
                    if(6 <= board[x][y] && board[x][y] <= 10) {       // 웜홀이라면
                        int wormholeIdx = board[x][y] - 6;
                        if(wormhole[wormholeIdx][0] == -1) {
                            wormhole[wormholeIdx][0] = x;
                            wormhole[wormholeIdx][1] = y;
                        } else {
                            wormhole[wormholeIdx][2] = x;
                            wormhole[wormholeIdx][3] = y;
                        }
                    }
                }
            }
            // 외벽에 5번 블록 설치
            generateWall();

            // 모든 위치를 핀볼의 시작점으로 하는 완전 탐색
            for(int x=1; x<=n; x++) {
                for(int y=1; y<=n; y++) {
                    if(board[x][y] != 0) {      // 빈 공간만 시작점이 될 수 있다
                        continue;
                    }
                    for(int d=0; d<4; d++) {    // 출발할 때 4가지 방향 모두 시도
                        answer = Math.max(answer, play(x, y, d));
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}