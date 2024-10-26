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
    
    final static int SIZE = 100;
    final static int[] dx = {-1, 1, 0, 0};
    final static int[] dy = {0, 0, -1, 1};
    
    static int answer;		// 도달 가능 여부
    static int[][] arr;
    static Position start;
    static Position end;
    static boolean[][] visit;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
		//T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int test_num = sc.nextInt();
            answer = 0;
            arr = new int[SIZE][SIZE];
            visit = new boolean[SIZE][SIZE];
            
            // 미로 입력하기
            for(int i=0; i<SIZE; i++) {
                String str = sc.next();
            	for(int j=0; j<SIZE; j++) {
                	arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                    if(arr[i][j] == 2) {		// 출발점이라면
                    	start = new Position(i, j);
                    } else if(arr[i][j] == 3) {		// 도착점이라면
                    	end = new Position(i, j);
                    }
                }
            }
            
            dfs(start);
            
            System.out.println("#" + test_num + " " + answer);
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
        	if(!isValid(nx, ny) || visit[nx][ny] || arr[nx][ny] == 1) {		// 유효하지 않은 인덱스거나 방문했거나 벽이라면
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