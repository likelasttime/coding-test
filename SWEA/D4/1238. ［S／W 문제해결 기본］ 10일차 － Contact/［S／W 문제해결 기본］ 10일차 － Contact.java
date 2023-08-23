import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	
	static int answer;	// 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람
	static int[][] arr;
	static int n;		// 데이터 길이

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for(int t=1; t<=10; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());		// 데이터 길이
			int start = Integer.parseInt(st.nextToken()); 	// 시작점
			
			st = new StringTokenizer(br.readLine());
			arr = new int[101][101];
			for(int i=0; i<n/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				arr[from][to] = 1;	// from이 to에 전화할 수 있음
			}
			
			bfs(start);
			bw.append("#").append(String.valueOf(t)).append(" ").append(String.valueOf(answer)).append("\n");
		}
		bw.flush();
	}
	
	
	public static void bfs(int start) {
		Deque<int[]> que = new ArrayDeque();
		boolean[] visited = new boolean[101];
		int[] depth = new int[101];
		que.offer(new int[] {start, 0});
		visited[start] = true;
		int maxDepth = 0;		// BFS 실행 후 최대 깊이

		while(!que.isEmpty()) {		// 원소가 있을 동안에
			int[] node = que.poll();
			maxDepth = Math.max(maxDepth, node[1]);
			depth[node[1]] = Math.max(depth[node[1]], node[0]);
			for(int i=1; i<101; i++) {
				if(visited[i]) {
					continue;
				}
				if(arr[node[0]][i] == 0) {
					continue;
				}
				que.offer(new int[] {i, node[1] + 1});
				visited[i] = true;		
			}
		}
		answer = depth[maxDepth];		// 최대 깊이에서 가장 큰 값
	}

}
