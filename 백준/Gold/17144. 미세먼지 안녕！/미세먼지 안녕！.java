import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/*
 * 미세먼지 확산은 동시에 일어나기 때문에 큐에 확산하기 전의 미세먼지 양을 저장해야 한다.
 * 이렇게 하지 않으면, 인접한 미세먼지의 확산에 영향을 받게 된다.
 * 모든 미세먼지를 큐에 넣은 후 인접한 네 방향으로 확산시킨다.
 */

public class Main {
	
	static int[][] arr;
	static int r;		// 6 <= 행 크기 <= 50
	static int c;		// 6 <= 열 크기 <= 50
	static int[][] airFresh = new int[2][2];		// 공기 청정기의 좌표
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Deque<Dust> deque = new ArrayDeque<Dust>();
	
	static class Dust {
		int x;
		int y;
		int dust;		// 미세먼지 양
		
		public Dust(int x, int y, int dust) {
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int answer = 0;		// 남아있는 메시먼지의 양
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());	// 행
		c = Integer.parseInt(st.nextToken());	// 열
		int t = Integer.parseInt(st.nextToken());	// 초
		
		arr = new int[r][c];
		int airFreshIdx = 0; 
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == -1) {		// 공기청정기
					airFresh[airFreshIdx][0] = i;
					airFresh[airFreshIdx++][1] = j;
				}
			}
		}
		
		/*
		 * 1초 동안 미세먼지 확산, 공기청정기 작동이 순서대로 일어난다. 
		 */
		for(int i=0; i<t; i++) {
			collectDuty();		// 미세먼지는 모두 큐에 넣기
			spreadDirty();		// 미세먼지 확산
			operate();
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(arr[i][j] == -1) {		// 공기청정기는 건너띄기
					continue;
				}
				answer += arr[i][j];
			}
		}
		
		System.out.println(answer);		// T초가 지난 후 남아있는 미세먼지의 양을 출력
	}
	
	/*
	 * 미세먼지를 모두 큐에 담는다.
	 */
	public static void collectDuty() {
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				if (arr[i][j] == -1 || arr[i][j] == 0) {		// 공기청정기와 빈칸은 건너띄기
					continue;
				}
				deque.add(new Dust(i, j, arr[i][j]));		// 먼지를 큐에 담기
			}
		}
	}
	
	/*
	 * 미세먼지 확산
	 */
	public static void spreadDirty() {
		while (!deque.isEmpty()) {
			Dust dust = deque.poll();
			int x = dust.x;
			int y = dust.y;
			int val = dust.dust / 5;		// 확산될 미세먼지의 값
			if (val == 0) continue;		// 확산될 미세먼지가 없음
			int cnt = 0;		// (x,y) 좌표에서 확산된 미세먼지의 수
			for (int d=0; d<4; d++) {	// 네 방향으로 퍼진다.
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(isNotValid(nx, ny) || arr[nx][ny] == -1) {	// 인덱스 범위 초과하거나 공기청정기라면 건너띄기
					continue;
				}
				arr[nx][ny] += val;		// (x, y)좌표로부터 미세먼지가 확산됨
				cnt++;		// (x,y) 좌표에서 확산된 미세먼지의 수가 증가함
			}
			arr[x][y] -= val * cnt;
		}
	}
	
	// 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동
    private static void operate() {
        
        int top = airFresh[0][0];
        int down = airFresh[1][0];
        
        // 위쪽 공기청정기의 바람은 반시계방향 순환,
        // 아래로 당기기
        for (int i = top - 1; i > 0; i--) 
            arr[i][0] = arr[i-1][0];
        // 왼쪽으로 당기기
        for (int i = 0; i < c - 1; i++) 
            arr[0][i] = arr[0][i+1];
        // 위로 당기기
        for (int i = 0; i < top; i++) 
            arr[i][c - 1] = arr[i + 1][c - 1];
        // 오른쪽으로 당기기
        for (int i = c - 1; i > 1; i--) 
            arr[top][i] = arr[top][i-1];
        // 공기청정기에서 부는 바람은 미세먼지가 없는 바람
        arr[top][1] = 0;
        
        // 아래쪽 공기청정기의 바람은 시계방향으로 순환
        // 위로 당기기
        for (int i = down + 1; i < r - 1; i++) 
            arr[i][0] = arr[i + 1][0];
        // 왼쪽으로 당기기
        for (int i = 0; i < c - 1; i++) 
            arr[r - 1][i] = arr[r - 1][i + 1]; 
        // 아래로 당기기
        for (int i = r - 1; i > down; i--) 
            arr[i][c - 1] = arr[i - 1][c - 1];
        // 오른쪽으로 당기기
        for (int i = c - 1; i > 1; i--) 
            arr[down][i] = arr[down][i - 1];
        // 공기청정기에서 부는 바람은 미세먼지가 없는 바람
        arr[down][1] = 0;
    }
	
	
	public static boolean isNotValid(int x, int y) {
		return 0 > x || x >= r || 0 > y || y >= c;		// true면 인덱스 범위 초과
	}
	
}