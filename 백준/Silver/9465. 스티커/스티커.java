import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 테스트 케이스 수
        for(int tc=0; tc<t; tc++) {
            int n = Integer.parseInt(br.readLine());        // 정수의 개수
            int[][] arr = new int[2][n];
            int[][] dp = new int[2][n + 1];
            for(int row=0; row<2; row++) {
                st = new StringTokenizer(br.readLine());
                for(int col=0; col<n; col++) {
                    arr[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            dp[0][1] = arr[0][0];
            dp[1][1] = arr[1][0];
            for(int col=2; col<=n; col++) {
                dp[0][col] = Math.max(dp[1][col - 2], dp[1][col - 1]) + arr[0][col - 1];
                dp[1][col] = Math.max(dp[0][col - 2], dp[0][col - 1]) + arr[1][col - 1];
            }
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}