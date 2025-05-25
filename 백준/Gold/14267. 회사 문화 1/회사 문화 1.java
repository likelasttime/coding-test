import java.util.*;
import java.io.*;

public class Main {
    static int[] answer;
    static int n, m;        // 2 <= n, m <= 100,000
    static List<List<Integer>> graph;       // 각 인덱스별로 부하직원 리스트를 저장

    public static void dfs(int person) {
        for(int i : graph.get(person)) {
            answer[i] += answer[person];
            dfs(i);
        }
    }

    public static void bfs(int start, int score) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);
        answer[start] += score;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int person : graph.get(current)) {
                if (!visited[person]) {
                    visited[person] = true;
                    queue.offer(person);
                    answer[person] += score;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());       // 회사의 직원 수
        m = Integer.parseInt(st.nextToken());       // 최초의 칭찬 횟수
        answer = new int[n + 1];
        graph = new ArrayList<>();
        for (int i=0; i<=n; i++) graph.add(new ArrayList<>());

        // 간선 입력
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {      // i = 부하직원 번호
            int person = Integer.parseInt(st.nextToken());
            if(person == -1) {
                continue;
            }
            graph.get(person).add(i);
        }

        // 직속 상사로부터 칭찬을 받은 직원 번호, 칭찬의 수치 입력 받기
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            answer[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }

        dfs(1);

        for(int i=1; i<=n; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
