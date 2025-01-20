import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 4 <= 게임 판의 크기 <= 100
        int[][] arr = new int[n + 1][n + 1];
        long[][] dp = new long[n + 1][n + 1];
        for(int x=1; x<=n; x++) {
            for(int y=1; y<=n; y++) {
                arr[x][y] = sc.nextInt();
            }
        }

        dp[1][1] = 1;

        for(int x=1; x<=n; x++) {
            for(int y=1; y<=n; y++) {
                int cnt = arr[x][y];
                if(cnt == 0) {
                    break;
                }
                if(cnt + x <= n) {
                    dp[cnt + x][y] += dp[x][y];
                }
                if(cnt + y <= n) {
                    dp[x][cnt + y] += dp[x][y];
                }
            }
        }
        System.out.print(dp[n][n]);
    }
}