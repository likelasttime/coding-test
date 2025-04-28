import java.util.*;
import java.io.*;

public class Main {
    final static int CHESS_SIZE = 8;

    static String[] board;
    
    public static char changeColor(char prev) {
        if(prev == 'B') {
            return 'W';
        }
        return 'B';
    }

    /*
        x, y를 시작으로 8 * 8 크기의 배열에서 칠해야 하는 정사각형 개수의 최솟값을 반환
     */
    public static int draw(int x, int y) {
        int result = 0;
        char prev = board[x].charAt(y);
        for(int row=x; row<x+CHESS_SIZE; row++) {
            for(int col=y; col<y+CHESS_SIZE; col++) {
                char cur = board[row].charAt(col);
                if(prev != cur) {
                    result++;
                }
                prev = changeColor(prev);
            }
            prev = changeColor(prev);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new String[n];
        int answer = 50 * 50;       // 칠해야 하는 정사각형의 최소 개수
        // 보드의 상태 입력받기
        for(int i=0; i<n; i++) {
            board[i] = br.readLine();       // B: 검은색, W: 흰색
        }

        // 체스판의 위치를 조정해가면서 탐색
        for(int i=0; i<=n-CHESS_SIZE; i++) {
            for(int j=0; j<=m-CHESS_SIZE; j++) {
                int cnt = draw(i, j);
                answer = Math.min(answer, Math.min(64 - cnt, draw(i, j)));
            }
        }

        System.out.print(answer);
    }
}