import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());    // 1 <= 구매하려고 하는 카드의 개수 n <= 1,000
        int[] p = new int[n + 1];
        int[] dp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=n; i++) {
            dp[i] = p[i];
            for(int j=1; j<i; j++) {
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j]);
            }
        }
        System.out.println(dp[n]);
    }
}
