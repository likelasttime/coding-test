import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=tc; t++) {
			int answer = 0;			// 최소 날짜 수
			int n = Integer.parseInt(br.readLine());		// 나무의 개수
			int[] arr = new int[n];
			
			st = new StringTokenizer(br.readLine());
			int max = 0;
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i]);		// 최대 높이
			}
			
			int even = 0;		// 짝수
			int odd = 0;		// 홀수
			for(int i=0; i<n; i++) {
				int diff = max - arr[i];		// 채워야하는 높이
				even += diff / 2;
				odd += diff % 2;
			}
				
			while(even > odd+1) {
				even -= 1;
				odd += 2;	
			}
				
			if(even > odd) {		// 짝수가 더 큰 경우
				odd += 1;			// 1 -> 2 -> ...으로 짝수가 2번째가 되어야 처음 등장하니까 +1
			}else if(odd > even) {		// 홀수가 더 큰 경우
				answer += odd - even - 1;		
			}
			
			answer += even + odd;
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		System.out.print(sb);

	}
}