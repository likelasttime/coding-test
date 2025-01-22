import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 1 <= 상자의 개수 <= 1,000
        int[] arr = new int[n];
        int[] dp = new int[n];
        int answer = Integer.MIN_VALUE;
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
            dp[i] = 1;
            for(int j=0; j<i; j++) {
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        System.out.print(answer);
    }
}