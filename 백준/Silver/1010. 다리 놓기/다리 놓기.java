import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * @author 강민정
 * 
 * [아이디어]
 * 이항 계수를 구했다.
 * 
 */
public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int n;		// 서쪽 사이트의 개수
	static int m;		// 동쪽 사이트의 개수

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			/*
			 * 0 < n <= m < 30
			 */
			n = Integer.parseInt(st.nextToken()); // 서쪽 사이트의 개수
			m = Integer.parseInt(st.nextToken()); // 동쪽 사이트의 개수

			dp();
		}

		bw.flush();
	}

	public static void dp() throws IOException {
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0, end = Math.min(n, i); j <= end; j++) {
				if (i == j || j == 0) {
					dp[i][j] = 1;
					continue;
				}
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
		bw.append(String.valueOf(dp[m][n])).append("\n");
	}

}