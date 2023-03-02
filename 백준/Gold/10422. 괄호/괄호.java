import java.io.IOException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        long[] dp = new long[5001];
        dp[0] = 1;

        for(int i=2; i<5001; i+=2) {
            for(int j=2; j <= i; j+=2) {
                dp[i] += dp[j - 2] * dp[i - j];
                dp[i] %= 1000000007;
            }
        }

        for(int t=0; t<T; t++) {
            int L = sc.nextInt();
            System.out.println(dp[L] % 1000000007);
        }
    }
}
