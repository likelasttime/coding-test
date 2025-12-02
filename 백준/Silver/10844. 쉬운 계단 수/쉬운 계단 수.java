import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 입력을 받기 위한 Scanner
        Scanner sc = new Scanner(System.in);

        // N을 입력받음
        int N = sc.nextInt();
        final int MOD = 1000000000;

        // dp 테이블 초기화 (dp[n][i]: 길이가 n인 계단 수 중 마지막 자리가 i인 경우의 개수)
        long[][] dp = new long[N + 1][10];

        // 길이가 1일 때 초기 값 설정 (1 ~ 9)
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        // dp 배열을 채워나감 (점화식을 이용)
        for (int n = 2; n <= N; n++) {
            for (int i = 0; i <= 9; i++) {
                if (i > 0) {
                    dp[n][i] += dp[n - 1][i - 1];
                }
                if (i < 9) {
                    dp[n][i] += dp[n - 1][i + 1];
                }
                dp[n][i] %= MOD;  // 결과가 너무 커지지 않도록 MOD로 나눈 나머지를 저장
            }
        }

        // 길이가 N인 계단 수의 합을 구함
        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[N][i];
            result %= MOD;
        }

        // 결과 출력
        System.out.println(result);
    }
}
