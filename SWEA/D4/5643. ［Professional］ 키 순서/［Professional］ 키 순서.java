import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 나보다 작은 친구의 수 + 큰 친구의 수가 전체 학생 수 - 1이 되면 나의 등수를 알 수 있다.
 * 2차원 인접 행렬을 만들었고 arr[a][b] = a보다 b가 크다는 의미로 1을 넣었다.
 * 한 명씩 BFS 탐색을 해서 나보다 작은 친구의 수와 큰 친구의 수를 구한다.
 * 
 */

public class Solution {
	
	static int n;		// 학생 수
	static int arr[][];		// 인접행렬

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());		// 1 <= 테스트 케이스 수 <= 15 	
		for (int t=1; t<=tc; t++) {
			int answer = 0;		// 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지
			n = Integer.parseInt(br.readLine());		// 2 <= 학생들의 수 <= 500
			int m = Integer.parseInt(br.readLine()); 		// 0 <= 두 학생의 키를 비교한 횟수 <= n(n-1)/2
			arr = new int[n+1][n+1];		// 인덱스를 1부터 시작하려고 +1씩 함
			/*
			 * 두 학생의 키를 비교한 결과를 나타내는 두 양의 정수 a, b를 입력받기
			 * 번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미함
			 */
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				arr[a][b] = 1;		// b가 a보다 크다
			}
			
			for (int k=1; k<=n; k++) {
				int smallerCnt = minBfs(k);		// 자신보다 작은 친구를 찾는 BFS 탐색
				int biggerCnt = maxBfs(k);			// 자신보다 큰 친구를 찾는 BFS 탐색
				
				if (smallerCnt + biggerCnt == n-1) {
					answer++;
				}
			}
			
			
			bw.write("#" + t + " " + answer + "\n");
		}
		
		bw.flush();
	}
	
	/*
	 * 나보다 작은 친구 찾기
	 * start = 나 자신의 번호
	 */
	public static int minBfs (int start) {
		int result = 0;
		Deque<Integer> deque = new ArrayDeque<>();
		deque.add(start);
		boolean[] visited = new boolean[n + 1];
		visited[start] = true;			// 시작점 방문 처리
		
		while (!deque.isEmpty()) {
			int node = deque.pollFirst();		// 첫 번째 원소를 꺼내기
			
			for (int i=1; i<=n; i++) {
				if (visited[i]) {		// 방문한 곳은 건너띄기
					continue;
				}
				if (arr[i][node] == 1) {		// 나보다 작은 친구면 1로 표시된다.
					visited[i] = true;
					deque.add(i);
					result++;
				}
			}
		}
		
		return result;			// 나보다 작은 친구의 수
	}
	
	/*
	 * 나보다 큰 친구 찾기
	 * start = 나 자신의 번호
	 */
	public static int maxBfs (int start) {
		int result = 0;
		Deque<Integer> deque = new ArrayDeque<>();
		deque.add(start);
		boolean[] visited = new boolean[n + 1];
		visited[start] = true;			// 시작점 방문 처리
		
		while (!deque.isEmpty()) {
			int node = deque.pollFirst();		// 첫 번째 원소를 꺼내기
			
			for (int i=1; i<=n; i++) {
				if (visited[i]) {		// 방문한 곳은 건너띄기
					continue;
				}
				if (arr[node][i] == 1) {		// 나보다 큰 친구면 1로 표시된다.
					visited[i] = true;
					deque.add(i);
					result++;
				}	
			}
		}
		
		return result;			// 나보다 큰 친구의 수
	}
}
