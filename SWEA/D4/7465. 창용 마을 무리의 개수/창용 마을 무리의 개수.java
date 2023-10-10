import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
		
	static int n;			// 0 <= 서로를 알고 있는 사람의 관계 수 <= n(n-1)/2
	static boolean[] visit;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());		// 테스트 케이스 수
	
		for (int t=1; t<=tc; t++) {
			int answer = 0;		// 무리의 개수
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());		// 1 <= 창용 마을에 사는 사람의 수 <= 100
			int m = Integer.parseInt(st.nextToken());		// 0 <= 서로를 알고 있는 사람의 관계 수 <= n(n-1)/2
			arr = new int[n+1][n+1];				// 인접행렬(1번부터 사람의 번호를 붙일려고)
			visit = new boolean[n+1];
			
			/*
			 * 서로를 알고 있는 두 사람의 번호가 주어진다.
			 * 같은 관계는 반복해서 주어지지 않는다.
			 */
			for (int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arr[a][b] = 1;
				arr[b][a] = 1;
			}
			
			for(int i=1; i<=n; i++) {
				if (visit[i]) continue;		// 이미 체크한 친구라면 건너띄기
				visit[i] = true;
				dfs(i, 1);
				answer++;
			}
			
			bw.write("#" + t + " " + answer + "\n");		// 무리의 개수
		}
		
		bw.flush();			// 모든 테케의 답 출력
	}
	
	
	public static void dfs(int num, int depth) {
		if (depth == n) {			// 종료 조건
			return;
		}
		
		for (int i=1; i<=n; i++) {
			if (!visit[i] && arr[num][i] == 1) {		// 방문안했고 num과 i번째 친구가 서로를 알고있음
				visit[i] = true;
				dfs(i, depth + 1);
			}
		}
		
	}

}
