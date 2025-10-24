import java.util.*;
import java.io.*;

class Main {
    final static int INF = 1_000_000_000;
    static int N;      // 도시 수
    static int M;      // 버스 수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int[][] price = new int[N][N];
        int[][] next = new int[N][N]; // next[u][v] = 다음에 갈 정점 인덱스 (0-based), 없으면 -1

        // 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(price[i], INF);
            Arrays.fill(next[i], -1);
            price[i][i] = 0; // 자기 자신으로 가는 비용은 0
            // next[i][i]는 사용하지 않음(경로 출력시 자기자신은 0으로 처리)
        }

        // 입력: 여러 엣지 중 최소 비용으로 저장하고 next를 설정
        for (int e = 0; e < M; e++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            if (C < price[A][B]) {
                price[A][B] = C;
                next[A][B] = B; // A에서 B로 바로 가는 경우 다음 노드는 B
            }
        }

        // 플로이드-워셜 (next 갱신 방식)
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                if (price[i][k] == INF) continue;
                for (int j = 0; j < N; j++) {
                    if (price[k][j] == INF) continue;
                    int val = price[i][k] + price[k][j];
                    if (val < price[i][j]) {
                        price[i][j] = val;
                        next[i][j] = next[i][k]; // i->j 로 가려면 i에서 먼저 next[i][k]로 가야함
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        // 1) 비용 출력 (도달 불가능하면 0)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int ans = price[i][j];
                if (ans >= INF) ans = 0;
                sb.append(ans).append(' ');
            }
            sb.append('\n');
        }

        // 2) 경로 개수와 경로 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || price[i][j] >= INF) {
                    sb.append(0).append('\n'); // 자기자신이거나 도달불가일 때 0
                    continue;
                }
                // 경로 재구성: next 테이블을 따라감
                List<Integer> route = new ArrayList<>();
                int cur = i;
                route.add(cur + 1); // 출력은 1-based
                while (cur != j) {
                    cur = next[cur][j];
                    // 안전 체크(이론상 next가 -1이면 도달 불가인데 위 조건으로 걸러졌음)
                    if (cur == -1) break;
                    route.add(cur + 1);
                }
                sb.append(route.size()).append(' ');
                for (int idx = 0; idx < route.size(); idx++) {
                    sb.append(route.get(idx)).append(idx + 1 == route.size() ? '\n' : ' ');
                }
            }
        }

        System.out.print(sb.toString());
    }
}
