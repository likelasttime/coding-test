import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());    // 1 <= 삼각형의 크기 <= 500
        int[][] triangle = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=i; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[1][1] = triangle[1][1];
        for(int stair=2; stair<=n; stair++) {
            for(int col=1; col<=stair; col++) {
                if(col == 1) {
                    dp[stair][col] = dp[stair - 1][col] + triangle[stair][col];
                } else if(col == stair) {
                    dp[stair][col] = dp[stair - 1][col - 1] + triangle[stair][col];
                } else {
                    dp[stair][col] = Math.max(dp[stair - 1][col - 1], dp[stair - 1][col]) + triangle[stair][col];
                }
            }
        }

        int answer = 0;
        for(int col=1; col<=n; col++) {
            answer = Math.max(answer, dp[n][col]);
        }

        System.out.print(answer);
    }
}
