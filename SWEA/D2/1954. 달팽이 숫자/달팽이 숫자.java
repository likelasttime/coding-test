import java.util.Scanner;

public class Solution {
    static int[][] arr;
    static int n;

    final static int[] DX = {0, 1, 0, -1};
    final static int[] DY = {1, 0, -1, 0};

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void dfs(int cur, int d, int x, int y) {
        if(cur > n * n) {
            return;
        }
        int nx = DX[d] + x;
        int ny = DY[d] + y;
        if(!isValid(nx, ny) || arr[nx][ny] != 0) {
            dfs(cur, (d + 1) % 4, x, y);
        } else {
            arr[nx][ny] = cur++;
            dfs(cur, d, nx, ny);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();        // 전체 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            arr = new int[n][n];

            dfs(1, 0, 0, -1);

            System.out.println("#" + tc);
            for(int x=0; x<n; x++) {
                for(int y=0; y<n; y++) {
                    System.out.print(arr[x][y] + " ");
                }
                System.out.println();
            }
        }
    }
}