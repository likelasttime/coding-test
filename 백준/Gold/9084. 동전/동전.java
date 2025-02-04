import java.util.Scanner;

class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();       // 1 <= 테스트 케이스의 개수 <= 10
        for(int tc=0; tc<t; tc++) {
            int n = sc.nextInt();       // 1 <= 동전의 가지 수 <= 20
            int[] arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = sc.nextInt();
            }
            int m = sc.nextInt();       // 1 <= 주어진 N가지 동전으로 만들어야 할 금액 <= 10,000
            int[] dp = new int[m + 1];
            dp[0] = 1;
            for(int i=0; i<n; i++) {
                for(int j=arr[i]; j<=m; j++) {
                    dp[j] += dp[j - arr[i]];
                }
            }
            System.out.println(dp[m]);
        }
    }
}