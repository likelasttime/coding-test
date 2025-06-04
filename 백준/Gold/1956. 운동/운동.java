import java.util.*;
import java.io.*;

public class Main {
    final static int INF = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());       // 2 <= 마을의 수 <= 400
        int e = Integer.parseInt(st.nextToken());       // 0 <= 도로의 수 <= v(v-1)
        int[][] arr = new int[v + 1][v + 1];
        for(int i=1; i<=v; i++) {
            Arrays.fill(arr[i], INF);
            arr[i][i] = 0;
        }
        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a][b] = c;
        }

        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                for(int k=1; k<=v; k++) {
                    arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                }
            }
        }

        int answer = INF;
        for(int i=1; i<=v; i++) {
            for(int j=1; j<=v; j++) {
                if(i == j) {
                    continue;
                }
                answer = Math.min(answer, arr[i][j] + arr[j][i]);
            }
        }

        // 최소 사이클의 도로 길이의 합 출력
        if(answer == INF) {
            answer = -1;        // 운동 경로 찾는 것이 불가능
        }
        System.out.print(answer);
    }
}