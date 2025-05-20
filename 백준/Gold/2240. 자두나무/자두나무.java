import java.util.*;
import java.io.*;

public class Main {
    static int t;
    static int w;
    static int[][][] dp;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken()); // 자두가 떨어지는 초 (1 <= t <= 1000)
        w = Integer.parseInt(st.nextToken()); // 최대 움직임 횟수 (1 <= w <= 30)
        dp = new int[3][t + 1][w + 2];  // 나무 1, 2번에 대한 상태 (1번, 2번 나무와 이동 횟수)
        tree = new int[t + 1]; // 자두가 떨어지는 나무의 위치

        // 자두가 떨어지는 나무 위치를 입력받음
        for (int i = 1; i <= t; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        for(int time=1; time<=t; time++) {
            for(int move=1; move<=w+1; move++) {    // move - 1같은 경우 30번 이동한 것은 30 + 1만큼 봐야하니까 w + 1까지 탐색
                if(tree[time] == 1) {       // 1번 나무에서 자두가 떨어질때
                    // 1번 나무에 그대로 있는 경우와 2번 나무에서 1번 나무로 이동한 경우 => 1번 나무에서 자두를 먹음
                    dp[1][time][move] = Math.max(dp[1][time - 1][move], dp[2][time - 1][move - 1]) + 1;
                    // 1번 나무에서 2번 나무로 이동한 경우와 2번 나무에 그대로 있는 경우 => 2번 나무에 있어서 자두를 못 먹음
                    dp[2][time][move] = Math.max(dp[1][time - 1][move - 1], dp[2][time - 1][move]);
                } else {        // 2번 나무에서 자두가 떨어질 때
                    if(time == 1 && move == 1) continue;        // 처음에 1번 나무에서 있기 때문에 1초에 2번 나무로 가는 것은 불가능
                    // 2번 나무에 그대로 있는 경우와 1번 나무에서 2번 나무로 이동한 경우 => 2번 나무에서 자두를 먹음
                    dp[2][time][move] = Math.max(dp[2][time - 1][move], dp[1][time - 1][move - 1]) + 1;
                    // 2번 나무에서 1번 나무로 이동한 경우와 1번 나무에 그대로 있는 경우 => 1번 나무에 있어서 자두를 못 먹음
                    dp[1][time][move] = Math.max(dp[2][time - 1][move - 1], dp[1][time - 1][move]);
                }
            }
        }

        int answer = 0;
        for(int move=1; move<=w+1; move++) {
            answer = Math.max(answer, Math.max(dp[1][t][move], dp[2][t][move]));
        }
        System.out.print(answer);
    }
}