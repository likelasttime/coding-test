import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int answer = Integer.MAX_VALUE;
    static int n;        // 도시의 수
    static int[][] arr;
    static int[] visitOrder;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());        // 도시의 수
        
        /*
         * 비용 행렬이 주어진다.
         */
        arr = new int[n+1][n+1];
        visitOrder = new int[n];
        visit = new boolean[n+1];
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }        
        }
        
        dfs(0);
        System.out.println(answer);			// 외판원 순회에 필요한 최소 비용을 출력

    }
    
    /*
     * 순열
     * cnt = 고른 도시 갯수
     */
    public static void dfs(int cnt) {
    	if(cnt > 1 && arr[visitOrder[cnt-2]][visitOrder[cnt-1]] == 0) {		// 경로가 없으면 가지치기를 해서 더이상 이 경우의 수는 탐색하지 않음
    		return;
    	}
    	
        if(cnt == n) {            // 모든 도시를 방문 완료
            int prev = visitOrder[0];		// 출발점
            int sum = 0;
            for(int i=1; i<n; i++) {
                int end = visitOrder[i];		// 도착점   
                sum += arr[prev][end]; 
                prev = visitOrder[i];
            }
         
            if(arr[visitOrder[n-1]][visitOrder[0]] == 0) {		// 출발점으로 돌아오지 못함
            	return;
            }
            
            answer = Math.min(answer, sum + arr[visitOrder[n-1]][visitOrder[0]]);		// 도착지 -> 출발지를 더해준다.
            
            return;
        }
        
        for(int i=1; i<=n; i++) {
            if(!visit[i]) {        // 방문하지 않은 곳
                visitOrder[cnt] = i;
                visit[i] = true;
                dfs(cnt + 1);
                visit[i] = false;
            }
        }    
    }

}