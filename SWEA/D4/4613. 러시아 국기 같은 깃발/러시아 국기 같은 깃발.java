import java.io.*;

public class Solution {
    static int n;
    static int m;
    static String[] arr;
    static int[][] colorCnt;

    /*
        각 행별로 흰색, 파란색, 빨간색 갯수 세기
     */
    public static void cnt() {
        for(int x=0; x<n; x++) {
            for(int y=0; y<m; y++) {
                char color = arr[x].charAt(y);
                if(color == 'W') {
                    colorCnt[x][0]++;
                } else if(color == 'B') {
                    colorCnt[x][1]++;
                } else {
                    colorCnt[x][2]++;
                }
            }
        }
    }

    public static int draw() {
        int minCnt = Integer.MAX_VALUE;
        int whiteCnt = 0;
        
        for(int i=0; i<n-2; i++) {
            whiteCnt += (m - colorCnt[i][0]);
            int blueCnt = 0;
            for(int j=i+1; j<n-1; j++) {
                int redCnt = 0;
                blueCnt += (m - colorCnt[j][1]);
                for(int k=j+1; k<n; k++) {
                    redCnt += (m - colorCnt[k][2]);
                }
                minCnt = Math.min(minCnt, whiteCnt + blueCnt + redCnt);
            }
        }
        return minCnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());        // 전체 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++) {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);       // 행
            m = Integer.parseInt(input[1]);       // 열
            arr = new String[n];
            colorCnt = new int[n][3];
            
            for(int i=0; i<n; i++) {
                arr[i] = br.readLine();
            }
            
            cnt();
            System.out.println("#" + tc + " " + draw());
        }
    }
}