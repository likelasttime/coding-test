import java.io.*;
import java.util.StringTokenizer;

class Solution {
    final static int[] DX = {1, -1, -1, 1};
    final static int[] DY = {-1, -1, 1, 1};

    static int n;       // 4 <= 한 변의 길이 <= 20
    static int[][] arr;
    static boolean[][][] visit;
    static int width;       // 움직일 가로 크기
    static int height;      // 움직일 세로 크기
    static boolean[] desert;   // 디저트 종류(1 ~ 100)
    static int curX;
    static int curY;
    static int answer;      // 최대 디저트 수

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    /*
        x,y 지점에서 d 방향으로 scale만큼 움직이기
     */
    public static boolean move(int d, int scale) {
        int nx = curX;
        int ny = curY;
        while(scale > 0) {
            nx += DX[d];
            ny += DY[d];
            if(!isValid(nx, ny) || desert[arr[nx][ny]]) {      // 좌표가 범위를 벗어나거나 같은 디저트라면
                return false;
            }
            visit[nx][ny][d] = true;
            desert[arr[nx][ny]] = true;
            scale--;
        }
        curX = nx;
        curY = ny;
        return true;
    }

    /*
        원점에서 시작해서 사각형을 그리면서 움직이기
     */
    public static void drawRectangle(int startX, int startY, int d) {
        curX = startX;
        curY = startY;
        desert = new boolean[101];

        // 4번 움직이기
        for(int i=0; i<2; i++) {
            boolean flag = move(d, width);
            d = (d + 1) % 4;        // 방향 전환

            if(!flag) {     // 더이상 탐색 불가
                return;
            }

            flag = move(d, height);
            d = (d + 1) % 4;

            if(!flag) {     // 더이상 탐색 불가
                return;
            }
        }

        // 도착한 지점이 원점이면
        if(curX == startX && curY == startY) {
            answer = Math.max(answer, (width + height) * 2);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());        // 테스트 케이스의 개수
        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            visit = new boolean[n][n][4];
            answer = -1;
            arr = new int[n][n];
            // 디저트 종류 입력 받기
            for(int row=0; row<n; row++) {
                st = new StringTokenizer(br.readLine());
                for(int col=0; col<n; col++) {
                    arr[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            // 모든 좌표를 원점으로 탐색
            for(int row=0; row<n; row++) {
                for(int col=0; col<n; col++) {
                    for(int d=0; d<4; d++) {
                        if(visit[row][col][d]) {  // 현재 위치를 지금 이 방향으로 탐색했었다면
                            continue;
                        }
                        // 가로, 세로 길이 정하기(가로, 세로의 최소는 1이고 최대는 n-1)
                        for(int w=1; w<n; w++) {
                            for(int h=1; h<n; h++) {
                                width = w;
                                height = h;
                                drawRectangle(row, col, d);
                            }
                        }
                    }
                }
            }
            bw.write("#" + tc + " " + answer + "\n");
        }
        bw.flush();
    }
}