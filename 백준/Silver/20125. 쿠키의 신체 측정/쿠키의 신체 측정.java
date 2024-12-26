import java.io.*;

public class Main {
    // 상, 좌, 우, 하
    final static int[] DX = {-1, 0, 0, 1};
    final static int[] DY = {0, -1, 1, 0};

    static String[] board;
    static int n;

    public static boolean isCookieHeart(int x, int y) {
        for (int d=0; d<4; d++) {
            int nx = x + DX[d];
            int ny = y + DY[d];
            if(isOutRange(nx, ny)) {
                return false;
            }
            if(board[nx].charAt(ny) != '*') {       // 쿠키의 신체가 아니라면
                return false;
            }
        }
        return true;
    }

    public static int getLegLength(int x, int y) {
        int len = 0;
        int copiedX = x;
        int copiedY = y;
        while(true) {
            int nx = copiedX + DX[3];
            int ny = copiedY + DY[3];
            if(isOutRange(nx, ny)) {
                return len;
            }
            if(board[nx].charAt(ny) == '_') {
                return len;
            }
            len++;
            copiedX = nx;
            copiedY = ny;
        }
    }

    public static boolean isOutRange(int nx, int ny) {
        if(0 > nx || nx >= n || 0 > ny || ny >= n) {        // 유효하지 않은 좌표
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());     // 판의 한변의 길이
        board = new String[n];

        for(int i=0; i<n; i++) {
            board[i] = br.readLine();
        }

        boolean isFindHeart = false;
        int heartX = -1;
        int heartY = -1;
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                if (board[x].charAt(y) == '_') {        // 쿠키의 신체가 아니라면
                    continue;
                }
                if(isCookieHeart(x, y)) {       // 쿠키의 심장을 찾았다면
                    isFindHeart = true;
                    heartX = x;
                    heartY = y;
                    break;
                }
            }
            if(isFindHeart) {       // 쿠키의 심장을 찾아서 더 탐색 안 해도 됨
                break;
            }
        }

        // 심장의 위치 출력
        System.out.println((heartX + 1) + " " + (heartY + 1));

        // 왼쪽 팔, 오른쪽 팔, 허리 길이 재기
        int waistX = -1;
        int waistY = -1;
        for(int d=1; d<4; d++) {
            int len = 0;
            int copiedX = heartX;
            int copiedY = heartY;
            while(true) {
                int nx = copiedX + DX[d];
                int ny = copiedY + DY[d];
                if(isOutRange(nx, ny)) {
                    break;
                }
                if(board[nx].charAt(ny) == '_') {
                    break;
                }
                len++;
                copiedX = nx;
                copiedY = ny;
                waistX = nx;
                waistY = ny;
            }
            System.out.print(len + " ");
        }

        // 왼쪽 다리, 오른쪽 다리 길이 재기
        System.out.print((getLegLength(waistX + 1, waistY - 1) + 1) + " ");
        System.out.print((getLegLength(waistX + 1, waistY + 1) + 1));
    }
}