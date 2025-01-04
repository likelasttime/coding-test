import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    final static int SIZE = 9;

    static int[][] arr;

    /*
        가로 탐색
     */
    public static boolean checkRow() {
        for(int x=0; x<SIZE; x++) {
            boolean[] visit = new boolean[SIZE + 1];
            for(int y=0; y<SIZE; y++) {
                if(visit[arr[x][y]]) {
                    return true;
                }
                visit[arr[x][y]] = true;
            }
        }
        return false;
    }

    /*
        세로 탐색
     */
    public static boolean checkCol() {
        for(int x=0; x<SIZE; x++) {
            boolean[] visit = new boolean[SIZE + 1];
            for(int y=0; y<SIZE; y++) {
                if(visit[arr[y][x]]) {
                    return true;
                }
                visit[arr[y][x]] = true;
            }
        }
        return false;
    }

    /*
        정사각형 탐색
     */
    public static boolean checkRectangle() {
        int rowStart = 0, colStart = 0;
        for(int i=0; i<9; i++) {        // 9개의 사각형 탐색
            boolean[] visit = new boolean[SIZE + 1];
            for(int x=rowStart; x<rowStart+2; x++) {
                for(int y=colStart; y<colStart+2; y++) {
                    if(visit[arr[x][y]]) {
                        return true;
                    }
                    visit[arr[x][y]] = true;
                }
            }
            if(colStart == 6) {
                rowStart += 3;
                colStart = 0;
            } else {
                colStart += 3;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 전체 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++) {
            int answer = 1;
            arr = new int[SIZE][SIZE];
            for(int x=0; x<SIZE; x++) {
                st = new StringTokenizer(br.readLine());
                for(int y=0; y<SIZE; y++) {
                    arr[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            boolean isValidRow = checkRow();
            boolean isValidCol = checkCol();
            boolean isValidRectangle = checkRectangle();
            if(isValidRectangle || isValidRow || isValidCol) {
                answer = 0;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}