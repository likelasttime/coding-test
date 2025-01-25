import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 동전의 종류
        int k = sc.nextInt();       // 가치의 합
        int[] coin = new int[n + 1];
        int[] dp = new int[k + 1];
        dp[0] = 1;
        // 동전 입력받기
        for(int i=1; i<=n; i++) {
            coin[i] = sc.nextInt();
        }
        for(int i=1; i<=n; i++) {
            for(int j=coin[i]; j<=k; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }
        System.out.print(dp[k]);        // 경우의 수 출력
    }
}