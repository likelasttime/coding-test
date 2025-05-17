import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());        // 테스트 케이스 개수
        StringTokenizer st;
        for(int tc=0; tc<t; tc++) {
            int k = Integer.parseInt(br.readLine());  // 3 <= 소설을 구성하는 장의 수 <= 500
            int[] sumArr = new int[k + 1];
            int[][] dp = new int[k + 1][k + 1];

            // 1장부터 k장까지 수록한 파일의 크기 입력 받기
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=k; i++) {
                sumArr[i] = sumArr[i - 1] + Integer.parseInt(st.nextToken());
            }

            for(int cnt=1; cnt<k; cnt++) {       // 구간 길이
                for(int start=1; cnt+start<=k; start++) {       // 시작 인덱스
                    int to = start + cnt;       // 도착 인덱스
                    dp[start][to] = Integer.MAX_VALUE;
                    for(int mid=start; mid<to; mid++) {
                        /* start에서 to까지 파일을 하나로 합치는 최소 비용
                            = start부터 mid까지 파일을 합치는 최소 비용
                            + (mid+1)부터 to까지 파일을 합치는 최소 비용
                            + start부터 to까지 파일 전체 크기 (누적합)
                         */
                        dp[start][to] = Math.min(dp[start][to],
                                dp[start][mid] + dp[mid + 1][to] + sumArr[to] - sumArr[start - 1]);
                    }
                }
            }

            System.out.println(dp[1][k]);   // 첫 번째 파일에서 k개의 파일을 모두 합쳤을 때 최소 비용
        }
    }
}
