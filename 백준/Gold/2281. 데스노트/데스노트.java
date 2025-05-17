import java.util.*;
import java.io.*;

public class Main {
    final static int MAX_SIZE = 2000;

    static int n;
    static int m;
    static int[] arr;
    static int[][] dp;

    /*
        row: 현재 줄
        col: 작성한 칸의 수
     */
    public static int dfs(int row, int col) {
        if(row >= n) {      // 모든 사람의 이름을 다 적었다면
            return 0;
        }
        if(dp[row][col] != Integer.MAX_VALUE) {
            return dp[row][col];
        }
        int remain = m - col + 1;
        // 새 줄에 적을 때
        int num = dfs(row + 1, arr[row] + 1) + (remain * remain);
        // 이어서 적을 때
        if(col + arr[row] <= m) {
            num = Math.min(num, dfs(row + 1, col + arr[row] + 1));
        }
        dp[row][col] = num;
        return dp[row][col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 1 <= 사람 수 <= 1,000
        m = Integer.parseInt(st.nextToken());   // 1 <= 노트의 가로 칸의 개수 <= 1,000
        arr = new int[MAX_SIZE + 1];
        dp = new int[MAX_SIZE + 1][MAX_SIZE + 1];
        // 각 사람의 이름 길이 입력받기
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for(int i=0; i<=MAX_SIZE; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        System.out.print(dfs(0, 0));
    }
}
