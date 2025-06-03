import java.util.*;
import java.io.*;

public class Main {
    static int n;   // 2 <= 행성의 개수 <= 10
    static int k;   // 0 <= 행성의 위치 < n
    static int[][] arr;
    static int answer;

    public static void permutation(int start, int time, int visited){
        if(visited == (1<<n)-1){
            answer = Math.min(answer, time);
            return;
        }

        for(int i=0; i<n; i++){
            if((visited & (1<<i))!=0){
                continue;
            }
            permutation(i,time + arr[start][i], visited | (1<<i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        int INF = Integer.MAX_VALUE;

        // 각 행성 간 이동 시간 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드-워셜 알고리즘: 모든 행성 간의 최단 경로 계산
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                }
            }
        }

        answer = Integer.MAX_VALUE;
        permutation(k, 0, (1<<k));
        System.out.println(answer);
    }
}
