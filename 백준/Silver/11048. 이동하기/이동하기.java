import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 강민정
 * 
 * [아이디어] 
 * Bottom-Up 방식의 DP 구현
 * 세 방향 중에서 가장 큰 값을 선택한다.
 */
public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n;
		int m;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());		// 미로의 크기 1 <= n <= 1,000
		m = Integer.parseInt(st.nextToken());		// 미로의 크기 1 <= m <= 1,000
		
		int[][] arr = new int[n+1][m+1];
		int[][] dp = new int[n+1][m+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());		// (i, j)에 놓인 사탕의 개수 0 <= arr[i][j] <= 100
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int max = 0;
				if(i-1 >= 1 && j-1 >= 1) {
					max = Math.max(max, dp[i-1][j-1]);
				}
				if(i-1 >= 1) {
					max = Math.max(max, dp[i-1][j]);
				}
				if(j-1 >= 1) {
					max = Math.max(max, dp[i][j-1]);
				}
				dp[i][j] = arr[i][j] + max;
			}
		}
		
		System.out.println(dp[n][m]);		// 준규가 (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수의 최댓값
	}
			
}