import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 수열의 크기
        int[] arr = new int[N];
        int[][] dp = new int[N][2];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        dp[0][0] = 1;  // 첫 번째 수를 선택했을 때의 LIS 길이
        dp[0][1] = 0;  // 첫 번째 수를 선택하지 않았을 때의 LIS 길이

        // dp 계산
        for (int i = 1; i < N; i++) {
            dp[i][0] = 1; // 처음에 자신을 선택한 경우는 최소 1
            for (int j = i - 1; j >= 0; j--) {
                // arr[j]가 arr[i]보다 작으면, arr[i]를 선택할 수 있다.
                if (arr[j] < arr[i]) {
                    dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1); // arr[i]를 포함한 LIS
                }
            }
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]); // 이전까지의 LIS 최대값
        }

        System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));  // 마지막 수까지 포함한 LIS 최대값 출력
    }
}
