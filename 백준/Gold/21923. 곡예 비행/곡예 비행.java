import java.util.*;

public class Main {
    static final int MAX = 1001;
    static int[][] list = new int[MAX][MAX];
    static int[][][] dp = new int[MAX][MAX][2];
    static int[][][] direct = {
        {{0, 1}, {-1, 0}}, // 상승 비행 (위, 오른쪽)
        {{0, 1}, {1, 0}}   // 하강 비행 (위, 오른쪽)
    };
    static int N, M;

    public static int dfs(int x, int y, int flag) {
        // 종료 조건: (N-1, M-1)에 도달하면서 flag가 1일 때 (하강 비행 완료)
        if (x == N - 1 && y == M - 1 && flag == 1) {
            return list[x][y];
        }

        int ret = dp[x][y][flag];
        if (ret != Integer.MIN_VALUE) return ret; // 이미 계산된 값이면 반환

        if (flag == 0) {
            ret = dfs(x, y, 1); // 상승 비행 후 하강 비행으로 전환
        }

        // 2가지 방향으로 이동
        for (int d = 0; d < 2; d++) {
            int nx = x + direct[flag][d][0];
            int ny = y + direct[flag][d][1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 범위 벗어나면 skip

            ret = Math.max(ret, dfs(nx, ny, flag));
        }

        ret += list[x][y]; // 현재 위치 값 더함
        dp[x][y][flag] = ret; // 메모이제이션
        return ret;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                list[i][j] = sc.nextInt();
            }
        }
        
        // dp 배열 초기화 (Integer.MIN_VALUE로 초기화)
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        System.out.println(dfs(N - 1, 0, 0)); // DFS 시작: (N-1, 0)에서 시작, 상승 비행으로 시작
    }
}
