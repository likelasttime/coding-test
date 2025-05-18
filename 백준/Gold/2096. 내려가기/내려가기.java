import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());    // 1 <= 층수 <= 100,000
        int[][] arr = new int[n + 1][4];
        int[][] minDp = new int[n + 1][4];
        int[][] maxDp = new int[n + 1][4];
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=1; i<=3; i++) {
            minDp[1][i] = arr[1][i];
            maxDp[1][i] = arr[1][i];
        }
        for(int i=2; i<=n; i++) {       // 층수
            minDp[i][1] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + arr[i][1];
            maxDp[i][1] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + arr[i][1];

            minDp[i][2] = Math.min(minDp[i - 1][1], Math.min(minDp[i - 1][2], minDp[i - 1][3])) + arr[i][2];
            maxDp[i][2] = Math.max(maxDp[i - 1][1], Math.max(maxDp[i - 1][2], maxDp[i - 1][3])) + arr[i][2];

            minDp[i][3] = Math.min(minDp[i - 1][2], minDp[i - 1][3]) + arr[i][3];
            maxDp[i][3] = Math.max(maxDp[i - 1][2], maxDp[i - 1][3]) + arr[i][3];
        }

        int minAns = Integer.MAX_VALUE;
        int maxAns = 0;
        for(int i=1; i<=3; i++) {
            minAns = Math.min(minAns, minDp[n][i]);
            maxAns = Math.max(maxAns, maxDp[n][i]);
        }

        System.out.print(maxAns + " " + minAns);
    }
}
