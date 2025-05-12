import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());        // 테스트 케이스의 수
        StringTokenizer st;

        for(int tc=0; tc<t; tc++) {
            int k = Integer.parseInt(br.readLine());        // 3 <= 소설을 구성하는 장의 수 <= 500
            int[][] dp = new int[k + 1][k + 1];
            int[] sum = new int[k + 1];     // 누적합 배열
            st = new StringTokenizer(br.readLine());

            // 파일의 크기를 나타내는 양의 정수 k개를 입력받기
            for(int i=1; i<=k; i++) {
                sum[i] += sum[i - 1] + Integer.parseInt(st.nextToken());
            }

            for(int cnt=1; cnt<k; cnt++) {
                for (int start = 1; start+cnt <= k; start++) {
                    int to = start + cnt;
                    dp[start][to] = Integer.MAX_VALUE;
                    for (int mid = start; mid < to; mid++) {
                        dp[start][to] = Math.min(dp[start][to], dp[start][mid] + dp[mid + 1][to] + sum[to] - sum[start - 1]);
                    }
                }
            }

            System.out.println(dp[1][k]);
        }
    }
}