import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * <pre>
 * [아이디어]
 * DP
 * i번 물건을 사는 경우와 사지 않는 경우 중에서 가치가 더 큰쪽을 선택한다.
 * 
 * </pre>
 */

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());		// 테스트 케이스 개수
		
		for(int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());		// 물건의 개수 1<=n<=100
			int k = Integer.parseInt(st.nextToken());		// 가방의 부피 1<=k<=1000
			
			int[][] arr = new int[n+1][2];		// 행=물건번호, 열=부피와 가치
			int[][] dp = new int[n+1][k+1];
			for(int i=1; i<=n; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());		// 부피
				int c = Integer.parseInt(st.nextToken());		// 가치
				
				arr[i][0] = v;
				arr[i][1] = c;
			}
			
			for(int i=1; i<=n; i++) {		// 물건 번호
				for(int j=0; j<=k; j++) {	// 부피
					if(j >= arr[i][0]) {	// i번 물건을 담을 수 있는 부피라면
						dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i][0]] + arr[i][1]);
					}else {
						dp[i][j] = dp[i-1][j];
					}
				}
			}
			
			bw.append("#").append(String.valueOf(t)).append(" ").append(String.valueOf(dp[n][k])).append("\n");
		}
		bw.flush();		// 모든 테스트 케이스 정답 출력
	}
}