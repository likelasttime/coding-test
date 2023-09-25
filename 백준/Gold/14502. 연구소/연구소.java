import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    static int[][] arr;
    static int n;
    static int m;
    static int answer;        // 안전지대 개수
    static List<Pos> birus = new ArrayList<Pos>();
    
    public static class Pos {
        int x;
        int y;
        
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public Pos() {};
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());        // 세로 3 <= n <= 8
        m = Integer.parseInt(st.nextToken());        // 가로 3 <= m <= 8
        answer = 0;
        arr = new  int[n][m];
        
        for(int x=0; x<n; x++) {
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<m; y++) {
                arr[x][y] = Integer.parseInt(st.nextToken());
                if(arr[x][y] != 0 ) {    // 빈 벽이 아니면 안전영역이 될 수 없으니까 갯수를 감소시킴
                    answer--;
                    if (arr[x][y] == 2) {	// 바이러스 발견
                    	birus.add(new Pos(x, y));
                    }
                } 
            }
        }
        
        dfs(0);
        
        System.out.println(answer);        // 바이러스가 없는 안전영역의 최대 크기
    }
    
    /*
     * cnt:벽의 개수
     */
    public static void dfs(int cnt) {
        if(cnt == 3) {        
            // 복사하기
            int[][] tmp = new int[n][m];
            for (int i = 0; i < n; i++) {
                tmp[i] = arr[i].clone();
            }

            answer = Math.max(bfs(birus, tmp), answer);        // 바이러스가 퍼짐
            return; 
        }
        
        for(int x=0; x<n; x++) {
            for (int y=0; y<m; y++) {
                if(arr[x][y] == 0) {                            
                	arr[x][y] = 1;             // 벽
                	dfs(cnt + 1);
                	arr[x][y] = 0;
                }
            }
        }
    }
    
    /*
     * 바이러스가 퍼짐
     */
    public static int bfs(List<Pos> birusList, int[][] arr) {
        Deque<Pos> que = new ArrayDeque();
   
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        for(Pos birus : birusList) {
            que.push(new Pos(birus.x, birus.y));
        }
        
        while(!que.isEmpty()) {
            Pos node = que.pollFirst();        // 맨 앞 요소를 뺀다.
            int x = node.x;
            int y = node.y;
            
            for(int i=0; i<4; i++) {        // 네방향
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(!isValid(nx, ny)) {        // 인덱스가 범위가 유효하지 않음, 이미 방문
                    continue;
                }
                
                if(arr[nx][ny] == 0) {        // 빈칸이 아니라면 건너띄기(바이러스가 퍼질 수 없는 공간)
                    arr[nx][ny] = 2;    // 바이러스
                    que.add(new Pos(nx, ny));
                }
            }
        }
        return count(arr);
    }
    
    public static int count(int[][] arr) {
    	int total = 0;
    	
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			if (arr[i][j] == 0) {
    				total++;
    			}
    		}
    	}
    	
    	return total;
    }
    
    /*
     * 인덱스 범위 체크
     */
    public static boolean isValid(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;          // 유효한 경우
    }
}