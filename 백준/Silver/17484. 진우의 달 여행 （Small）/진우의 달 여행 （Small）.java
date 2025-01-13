import java.util.Scanner;
import java.util.Arrays;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        int[][][] dp = new int[n][m][3];
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] = sc.nextInt();
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        for(int i=0; i<m; i++) {
            dp[0][i][0] = arr[0][i];
            dp[0][i][1] = arr[0][i];
            dp[0][i][2] = arr[0][i];
        }

        for(int x=1; x<n; x++) {
            for(int y=0; y<m; y++) {
                if(y == 0) {      // 첫 번째 열이라면
                    dp[x][y][0] = Math.min(dp[x - 1][y + 1][1], dp[x - 1][y + 1][2]) + arr[x][y];
                    dp[x][y][1] = dp[x - 1][y][0] + arr[x][y];
                } else if(y == m - 1) {     // 마지막 열이라면
                    dp[x][y][1] = dp[x - 1][y][2] + arr[x][y];
                    dp[x][y][2] = Math.min(dp[x - 1][y - 1][0], dp[x - 1][y - 1][1]) + arr[x][y];
                } else {
                    dp[x][y][0] = Math.min(dp[x - 1][y + 1][2], dp[x - 1][y + 1][1]) + arr[x][y];
                    dp[x][y][1] = Math.min(dp[x - 1][y][2], dp[x - 1][y][0]) + arr[x][y];
                    dp[x][y][2] = Math.min(dp[x - 1][y - 1][1], dp[x - 1][y - 1][0]) + arr[x][y];
                }
            }
        }

        for(int y=0; y<m; y++) {
            for(int d=0; d<3; d++) {
                answer = Math.min(answer, dp[n - 1][y][d]);
            }
        }

        System.out.println(answer);
    }
}