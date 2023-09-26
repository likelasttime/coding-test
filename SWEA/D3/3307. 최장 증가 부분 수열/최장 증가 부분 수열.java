import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());		// 테스트케이스 수
		
		for(int t=1; t<=tc; t++) {
			int answer = 0;			// 최장 부분 증가 수열의 길이
			int n = Integer.parseInt(br.readLine());		// 1 <= 수열의 길이 <= 1,000
			int[] arr = new int[n];			// 수열
			int[] dp = new int[n];
			
			/* n개의 수열을 입력하기 */
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i=0; i<n; i++) {
				dp[i] = 1;					// 자신 혼자 구성되는 경우가 가질 수 있는 최소 LIS
				for (int j=0; j<i; j++) {		// 앞쪽 모든 대상 고려
					if (arr[j] < arr[i] && dp[i] < dp[j] + 1) {		// 나보다 값이 작고, 나를 뒤에 붙였을 때 부분 수열의 최장 길이가 더 길때
						dp[i] = dp[j] + 1;
						answer = Math.max(answer, dp[i]);
					}
				}
			}
				
			bw.write("#" + t + " " + answer + "\n");
		}
		
		bw.flush();
	}
	
}
