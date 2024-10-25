import java.util.Scanner;

class Solution
{
    static class Position {
    	int x;
        int y;
        
        public Position(int x, int y) {
        	this.x = x;
            this.y = y;
        }
    }
    
    final static int SIZE = 16;
    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};
    
    static int[][] arr;
    static Position start;
    static Position end;
    static boolean[][] visit;
    static int answer;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
		//T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int test_num = sc.nextInt();		// 테스트케이스 번호
            arr = new int[SIZE][SIZE];
            visit = new boolean[SIZE][SIZE];
            answer = 0;
            
            // 미로 입력받기
            for(int i=0; i<SIZE; i++) {
                String str = sc.next();
            	for(int j=0; j<SIZE; j++) {
                	arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                    if(arr[i][j] == 2) {		// 시작점
                    	start = new Position(i, j);
                    } else if(arr[i][j] == 3) {		// 도착점
                    	end = new Position(i, j);
                    }
                }
            }
            
            dfs(start);
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
    
    public static void dfs(Position cur) {
        if(cur.x == end.x && cur.y == end.y) {		// 도착
        	answer = 1;
            return;
        }
        
        for(int d=0; d<4; d++) {
            int nx = cur.x + dx[d];
            int ny = cur.y + dy[d];
            
        	if(!isValid(nx, ny)) {		// 인덱스 범위가 유효하지 않다면
            	continue;
            } else if(arr[nx][ny] == 1 || visit[nx][ny]) {		// 벽을 만나거나 방문했다면
            	continue;
            }
            visit[nx][ny] = true;
            dfs(new Position(nx, ny));
        }
    }
    
    public static boolean isValid(int x, int y) {
    	return 0 <= x && 0 <= y && x < SIZE && y < SIZE;
    }
}