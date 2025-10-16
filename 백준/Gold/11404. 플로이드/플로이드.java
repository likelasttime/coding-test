import java.util.*;
import java.io.*;

class Main {
    static final int INF = 10000001; // 충분히 큰 값 (오버플로 방지)
    static int N, M;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N + 1][N + 1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], c); // 중복 간선 처리
        }

        // 플로이드-워셜
        for (int k = 1; k <= N; k++) {       // 중간 경유지
            for (int i = 1; i <= N; i++) {   // 출발지
                for (int j = 1; j <= N; j++) { // 도착지
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
