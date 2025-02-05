import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Main {
    static int n;
    static int m;
    static int k;
    static List<City>[] lst;

    static class City {
        int to;
        int score;
        City(int to, int score) {
            this.to = to;
            this.score = score;
        }
    }

    public static int getMaxScore() {
        int answer = 0;
        int[][] dp = new int[m + 1][n + 1];
        Queue<Integer> que = new LinkedList();
        que.add(1);
        int choice = 1;
        while(!que.isEmpty() && choice < m) {     // 큐가 비어있지 않고, m개 이하로 선택할 때 까지 탐색
            int queSize = que.size();
            for(int i=0; i<queSize; i++) {
                int cur = que.poll();
                for(City connected : lst[cur]) {
                    if(dp[choice + 1][connected.to] >= connected.score + dp[choice][cur]) {
                        continue;
                    }
                    dp[choice + 1][connected.to] = connected.score + dp[choice][cur];
                    que.add(connected.to);
                }
            }
            choice++;
        }
        for(int i=2; i<=m; i++) {
            answer = Math.max(answer, dp[i][n]);
        }
        return answer;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());       // 1 <= 도시의 수 <= 300
        m = Integer.parseInt(st.nextToken());       // 2 <= 선택할 도시의 수 <= N
        k = Integer.parseInt(st.nextToken());       // 1 <= 개설된 항공로의 개수 <= 100,000
        lst = new ArrayList[n + 1];

        for(int i=0; i<=n; i++) {
            lst[i] = new ArrayList();
        }

        // 각 항공로에 대한 정보 입력받기
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            if(start > end) {       // 동쪽으로 이동하는 경로는 건너뛰기
                continue;
            }
            lst[start].add(new City(end, score));
        }

        System.out.print(getMaxScore());
    }
}