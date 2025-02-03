import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n + 2][2];
        int[] dp = new int[n + 2];
        for(int i=1; i<=n; i++) {
            arr[i][0] = sc.nextInt();   // 기간
            arr[i][1] = sc.nextInt();   // 금액
        }
        int max = 0;
        for(int i=1; i<=n+1; i++) {
            max = Math.max(max, dp[i]);
            if(arr[i][0] + i >= n + 2) {
                continue;
            }
            dp[arr[i][0] + i] = Math.max(dp[arr[i][0] + i], max + arr[i][1]);
        }
        System.out.print(dp[n + 1]);
    }
}