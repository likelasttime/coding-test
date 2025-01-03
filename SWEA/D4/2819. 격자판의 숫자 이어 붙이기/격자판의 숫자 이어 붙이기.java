import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Solution {
    final static int n = 4;
    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    static int[][] arr;
    static Set<String> set;
    static StringBuilder sb;

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void dfs(int x, int y, int depth) {
        if(depth == 7) {
            set.add(sb.toString());
            return;
        }
        for(int d=0; d<4; d++) {
            int nx = x + DX[d];
            int ny = y + DY[d];
            if(!isValid(nx, ny)) {
                continue;
            }
            sb.append(arr[nx][ny]);
            dfs(nx, ny, depth + 1);
            sb.deleteCharAt(depth);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();        // 전체 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++) {
            arr = new int[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            sb = new StringBuilder();
            set = new HashSet();
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    dfs(i, j, 0);
                }
            }
            System.out.println("#" + tc + " " + set.size());
        }
    }
}