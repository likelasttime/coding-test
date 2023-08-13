import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

class Solution {
    /** 왼쪽부터 순차적으로 사방탐색 */
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int n;		// 배열의 크기
	static int[][] check;		// 1:지뢰, 2:아직 클릭하지 않은 . , 3:클릭한 . , 0: 8방으로 지뢰가 없는 .
	static int answer;		// 최소 클릭 횟수
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T;
		T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

		for (int test_case = 1; test_case <= T; test_case++) {
			answer = 0;				// 최소 클릭 횟수
			n = Integer.parseInt(br.readLine());		// 배열의 크기
			check = new int[n][n]; 						// 지뢰, 클릭 상태를 체크하는 배열
			String[][] arr = new String[n][n];
			
            /** 입력으로 주어지는 지뢰 정보 배열을 입력 받기 */
            for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					arr[i][j] = String.valueOf(str.charAt(j));		// 문자열에서 문자를 추출 후 문자열로 변환해서 저장
					if (arr[i][j].equals("*")) { 						// 지뢰를 발견하면 1로 표시
						check[i][j] = 1;
					}
				}
			}

			/**
			 * 지뢰의 주변을 탐색
             * 지뢰가 있으면 1
             * 주위에 지뢰가 있는 .은 2
             * 지뢰가 주변에 아예 없으면 0
			 */
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (check[i][j] == 1) { 			// 지뢰를 발견
						for (int d = 0; d < 8; d++) {		// 8방 탐색
							int nx = i + dx[d];
							int ny = j + dy[d];
							if (0 > nx || n <= nx || 0 > ny || n <= ny) { 		// 범위 체크
								continue;
							}
							if(arr[nx][ny].equals(".")) {			
								check[nx][ny] = 2; 				// 주위에 지뢰가 있는 .
							}
						}
					}
				}
			}

			/**
            * 주위에 지뢰가 없는 곳(0)을 탐색
            * 지뢰가 아닌 칸은 방문 후 3으로 표시됨
            */
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (check[i][j] == 0) { 		// 8방으로 지뢰가 없는 곳
						bfs(i, j);
						answer++;				// 클릭횟수 증가
						}
					}
				}
			
            /** 아직 클릭하지 않은 곳은 값이 2이고, 한 번씩 누른다. */
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(check[i][j] == 2) {		// 아직 클릭하지 않은 곳
						answer++;				// 클릭 횟수 증가
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");			// 최소 클릭 횟수 출력
		}
		System.out.print(sb);		// 모든 테스트 케이스 실행 후 한번에 출력
	}
	
    /**
    * 값이 0인 곳을 시작으로 해서 탐색 중 0인 곳을 또 만나면 큐에 넣고 계속 탐색
    * 방문한 곳은 3으로 값을 바꾼다.
    */
	public static void bfs(int start, int end) {
		Deque<int[]> que = new LinkedList();
		que.add(new int[] {start, end});
		check[start][end] = 3;
		
		while(!que.isEmpty()) {
			int[] pos = que.poll();
            for(int i=0; i<8; i++) {		// 8방 탐색
				int nx = pos[0] + dx[i];
				int ny = pos[1] + dy[i];			
				
                if(0 > nx || nx >= n || 0 > ny || ny >= n) {		// 범위 체크
					continue;
				}
				
				if(check[nx][ny] == 0) {		// 0이라면
					que.add(new int[] {nx, ny});
				}	
                
				check[nx][ny] = 3;			// 방문한 곳은 3으로 표시
			}
		}
	}
}