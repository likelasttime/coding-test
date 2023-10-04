import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS 탐색
 * 같은 다리는 0보다 큰 자연수로 통일한다.
 * 가장 짧은 다리의 길이를 찾을 때 모든 육지에 대해서 BFS 탐색한다.
 * 단, 해당 육지는 한 곳이라도 바다가 인접해 있어야 한다.
 * 다리를 이을려면 시작점 육지와 도착점 육지의 값이 달라야 한다.
 */

public class Main {
	
	static int n;		// 지도의 크기 <= 100
	static int[][] arr;		// 지도(0: 바다, 1: 육지)
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] islandVisit;		// 같은 섬 체크할 때 사용하는 방문 배열
	
	static class Node {
		int x;
		int y;
		int distance;
		
		public Node (int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		int answer = 987654321;		// 가장 짧은 다리의 길이
		
		/*
		 * 같은 섬은 같은 번호로 표시하기
		 */
		islandVisit = new boolean[n][n];
		int islandNum = 1;		// 섬 번호
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (arr[i][j] == 0 || islandVisit[i][j]) {		// 섬이 아니거나 방문한 섬은 건너띄기
					continue;
				}
				bfs(i, j, islandNum++);
			}
		}
		
		/*
		 * 가장 짧은 다리의 길이를 찾는다.
		 * 모든 육지에 대해서 BFS 탐색을 한다.
		 */
		boolean[][] visited;
		for (int x=0; x<n; x++) {
			for (int y=0; y<n; y++) {
				if(arr[x][y] == 0 || !isPossible(x, y)) {	// 바다이거나 주변에 바다가 없어서 다리를 놓을 수 없으면 건너띄기
					continue;
				}
				/*
				 * 다리를 놓을 수 있으니까 BFS 탐색을 한다.
				 */
				Queue<Node> queue = new ArrayDeque<Node>();
				queue.add(new Node(x, y, 0));
				visited = new boolean[n][n];
				visited[x][y] = true;
				while (!queue.isEmpty()) {			// 큐에 원소가 들어있는 동안 반복
					Node node = queue.poll();		// 첫 번째 원소를 꺼내기
					if (node.distance > answer) {	// 더이상 탐색할 필요 없음
						break;
					}
					if(arr[node.x][node.y] > 0 && arr[node.x][node.y] != arr[x][y]) {		// 뽑은 값이 육지이고 출발했던 지점의 육지와 다른 종류다.
						answer = Math.min(answer, node.distance - 1);		// 거리 갱신(도착 지점은 포함하지 않음)
						break;
					}
					for (int d=0; d<4; d++) {
						int nx = node.x + dx[d];
						int ny = node.y + dy[d];
						if (!isValid(nx, ny) || visited[nx][ny] || arr[nx][ny] == arr[x][y]) {	// 좌표가 유효하지 않거나 방문했거나 같은 육지면 건너띄기
							continue;
						}
						queue.add(new Node(nx, ny, node.distance + 1)); 	// 다리를 놓아서 거리가 증가함
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		System.out.println(answer);		// 가장 짧은 다리 길이 출력

	}
	
	/*
	 * 입력 받는 함수
	 */
	public static void init () throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());		// 지도의 크기
		arr = new int[n][n];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	/*
	 * 다리를 놓을 수 있는지
	 * 네 방향 중 한 곳이라도 바다가 있어야 다리를 놓을 수 있는 가능성이 있다.
	 */
	public static boolean isPossible (int x, int y) {
		boolean flag = false;
		for (int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (!isValid(nx, ny) || arr[nx][ny] != 0) {		// 좌표가 범위를 넘어가거나 육지다.
				continue;
			}
			
			return true;
		}
		return false;		// 불가능
	}
	
	/*
	 * 인덱스 유효성 검사
	 */
	public static boolean isValid (int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;		// 유효한 좌표
	}
	
	/*
	 * 같은 섬에 같은 번호 주기
	 * num = 섬 번호
	 */
	public static void bfs (int x, int y, int num) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(new int[] {x, y});
		islandVisit[x][y] = true;
		arr[x][y] = num;
		while (!queue.isEmpty()) {		// 큐가 비어있지 않다면
			int[] pos = queue.poll();
			for (int d=0; d<4; d++) {
				int nx = pos[0] + dx[d];
				int ny = pos[1] + dy[d];
				if (!isValid(nx, ny) || islandVisit[nx][ny] || arr[nx][ny] == 0) {		// 좌표가 유효하지 않거나 방문했거나 바다라면 건너띄기
					continue;
				}
				islandVisit[nx][ny] = true;
				arr[nx][ny] = num;
				queue.add(new int[] {nx, ny});
			}
		}
	}

}