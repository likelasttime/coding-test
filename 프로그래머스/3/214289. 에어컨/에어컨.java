import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        final int INF = 1_000_000_000;
        int offset = 10; // 음수 온도 처리용
        int maxT = 50;   // 온도 범위 (-10 ~ 40)
        int n = onboard.length;

        int[][] dp = new int[n + 1][maxT + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], INF);
        dp[0][temperature + offset] = 0;

        for (int t = 0; t < n; t++) {
            for (int temp = 0; temp <= maxT; temp++) {
                if (dp[t][temp] == INF) continue;

                int realTemp = temp - offset;

                // 에어컨 OFF (자연 변화)
                int nextTemp = realTemp;
                if (realTemp < temperature) nextTemp++;
                else if (realTemp > temperature) nextTemp--;
                int nextIdx = nextTemp + offset;

                if (onboard[t] == 0 || (t1 <= nextTemp && nextTemp <= t2)) {
                    dp[t + 1][nextIdx] = Math.min(dp[t + 1][nextIdx], dp[t][temp]);
                }

                // 에어컨 ON (냉난방)
                for (int delta = -1; delta <= 1; delta++) {
                    int newTemp = realTemp + delta;
                    if (newTemp < -10 || newTemp > 40) continue;

                    int cost = (delta == 0) ? b : a;
                    if (onboard[t] == 0 || (t1 <= newTemp && newTemp <= t2)) {
                        dp[t + 1][newTemp + offset] = Math.min(
                            dp[t + 1][newTemp + offset],
                            dp[t][temp] + cost
                        );
                    }
                }
            }
        }

        int ans = INF;
        for (int temp = 0; temp <= maxT; temp++) ans = Math.min(ans, dp[n][temp]);
        return ans;
    }
}
