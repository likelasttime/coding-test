import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	private static final int MAX_VALUE = 1000 * 1000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int tc = Integer.parseInt(br.readLine());		// 테스트케이스 수
		
		for (int t=1; t<=tc; t++) {		
			int answer = MAX_VALUE;		// 최소 CC 값
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); 		// 사람 수
			int[][] dp = new int[n][n];
			
			// 값 입력받기
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {		
					int value = Integer.parseInt(st.nextToken());
					if (i == j) {		// 자기 자신은 스스로 연결되어 있지 않음
						continue;
					} else if (value == 0) {		// i와 j가 연결되어있지 않음
						dp[i][j] = MAX_VALUE;
					} else {
						dp[i][j] = 1;		// i와 j가 연결되어 있음
					}
				}
			}
			
			for (int k=0; k<n; k++) {		// 경유지
				for (int i=0; i<n; i++) {	// 출발점
					if (k == i) continue;		// 경유지와 출발점이 같으면 안 된다
					for (int j=0; j<n; j++) {	// 도착점
						if (i == j || j == k)	continue;		// 출발점과 도착점이 같을 수 없다
							dp[i][j] = Math.min(dp[i][j], dp[k][i] + dp[k][j]);
					}
				}
			}
			
			// 최소 CC 구하기
			for (int i=0; i<n; i++) {
				int total = 0;
				for (int j=0; j<n; j++) {
					total += dp[i][j];
				}
				answer = Math.min(answer, total);
			}
			bw.write("#" + t + " " + answer + "\n");
		}
		
		bw.flush();

	}
}
