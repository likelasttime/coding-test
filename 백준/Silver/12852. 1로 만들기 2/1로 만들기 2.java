import java.util.Scanner;

class Main {
    static int[] dp;
    static int[] answer;

    public static void dynamicProgramming(int i) {
        dp[i] = dp[i - 1] + 1;
        answer[i] = i - 1;
        if(i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
            answer[i] = i / 3;
            dp[i] = dp[i / 3] + 1;
        }
        if(i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
            answer[i] = i / 2;
            dp[i] = dp[i / 2] + 1;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[n + 1];
        answer = new int[n + 1];
        for(int i=2; i<=n; i++) {
            dynamicProgramming(i);
        }
        System.out.println(dp[n]);
        int i = n;
        while(true) {
            System.out.print(i + " ");
            i = answer[i];
            if(i <= 0) {
                break;
            }
        }
    }
}