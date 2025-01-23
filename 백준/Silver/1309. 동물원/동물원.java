import java.util.Scanner;

class Main {
    final static int MOD = 9901;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 1 <= 상자의 개수 <= 100,000
        int[][] dp = new int[n + 1][3];
        // 첫 번째 행에서 아무 것도 고르지 않을 때, 첫 번째 칸, 두 번째 칸 고르는 각각의 경우의 수는 1이다
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for(int x=2; x<=n; x++) {
            // 첫 번째 열은 x번째 행에 있는 칸을 모두 고르지 않았을 때 경우의 수 = x - 1번째 행의 경우의 수
            dp[x][0] = (dp[x - 1][0] + dp[x - 1][1] + dp[x - 1][2]) % MOD;
            // 두 번째 열은 x - 1번째 행에서 아무 것도 안 골랐을 때와 2번째 칸을 골랐을 때 경우의 수
            dp[x][1] = (dp[x - 1][0] + dp[x - 1][2]) % MOD;
            // 세 번째 열은 x - 1번째 행에서 아무 것도 안 골랐을 때와 1번째 칸을 골랐을 때 경우의 수
            dp[x][2] = (dp[x - 1][0] + dp[x - 1][1]) % MOD;
        }
        System.out.print((dp[n][0] + dp[n][1] + dp[n][2]) % MOD);
    }
}