import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS 탐색
 * visit 배열의 값에 최소값을 넣었다.
 * 즉, visit[x][y]는 (x, y)까지 오는데 필요한 최소 루피다.
 * 마지막에 visit[n-1][n-1]을 출력하면 도착 지점까지 오는 데 필요한 최소 루피를 구할 수 있다.
 * 원래 DFS로도 풀어봤는데 시간 초과가 난다. DFS로는 못 풀 것 같다.
 */

public class Main {
	
	public static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {0, 1, -1, 0};		
	static int[] dy = {1, 0, 0, -1};
	static int n;		// 동굴 크기
	static int[][] arr;		// 동굴
	static int[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int tc = 1;				// 테스트 케이스 수
		
		while (true) {		// 동굴의 크기를 0을 입력받기 전까지는 계속 실행			
			n = Integer.parseInt(br.readLine());					// 2 <= 동굴의 크기 <= 125
			if (n == 0) {
				break;		// 종료
			}
			arr = new int[n][n];		// n*n 크기의 동굴
			for (int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());	// 0 <= 도둑루피 <= 9
				}
			}
			visit = new int[n][n];
			for (int i=0; i<n; i++) {
				Arrays.fill(visit[i], Integer.MAX_VALUE);			// 방문 배열을 최대값으로 초기화시킨다.
			}
			bfs();
			bw.write("Problem " + tc++ + ": " + visit[n-1][n-1] + "\n");
		}
		bw.flush();
	}
	
	public static boolean isValid (int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;		// 인덱스 유효성 검사
	}
	
	public static void bfs () {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 0));		// 시작지점은 (0, 0)이다.
		visit[0][0] = arr[0][0];
		while (!queue.isEmpty()) {
			Node node = queue.poll();		// 첫 번째 원소를 꺼낸다.
				
			for (int d=0; d<4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				if (!isValid(nx, ny)) {
					continue;			// 유효하지 않으면 건너띄기
				}
				if (visit[nx][ny] > visit[node.x][node.y] + arr[nx][ny]) {		// (nx, ny)까지 오는 데 필요한 도둑루피를 최소값으로 갱신
					visit[nx][ny] = visit[node.x][node.y] + arr[nx][ny];
					queue.offer(new Node(nx, ny));
				}
			}
		}
	}

}
