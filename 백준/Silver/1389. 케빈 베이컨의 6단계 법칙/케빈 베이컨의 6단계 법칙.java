import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());       // 2 <= 유저의 수 <= 100
        int m = Integer.parseInt(st.nextToken());       // 1 <= 친구 관계의 수 <= 5,000
        int[][] arr = new int[n + 1][n + 1];

        for(int i=1; i<=n; i++) {
            Arrays.fill(arr[i], Integer.MAX_VALUE);
            arr[i][i] = 0;
        }

        // 친구 관계 입력받기
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        // 플로이드 워샬
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                for(int k=1; k<=n; k++) {
                    if (arr[j][i] != Integer.MAX_VALUE && arr[i][k] != Integer.MAX_VALUE) {
                        arr[j][k] = Math.min(arr[j][k], arr[j][i] + arr[i][k]);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        int minSum = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            int sum = 0;
            for(int j=1; j<=n; j++) {
                if(arr[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                sum += arr[i][j];
            }
            if(minSum > sum) {
                minSum = sum;
                answer = i;
            }
        }

        System.out.print(answer);
    }
}