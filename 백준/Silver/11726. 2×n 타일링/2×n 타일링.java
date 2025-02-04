import java.util.Scanner;
import java.util.Arrays;

class Main {
    static int n;
    static int[] dp;

    public static int dfs(int w) {
        if(dp[w] == -1) {
            dp[w] = (dfs(w - 1) + dfs(w - 2)) % 10007;
        }
        return dp[w];
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();       // 1 <= n <= 1,000
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = dp[1] = 1;
        dfs(n);
        System.out.print(dp[n]);
    }
}