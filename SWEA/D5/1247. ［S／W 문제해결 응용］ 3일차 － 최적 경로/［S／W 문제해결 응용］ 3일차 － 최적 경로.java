import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] visit;
	static int[][] customer;
	static int[] home;
	static int[] company;
	static int n;
	static int[] arr;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());	//  테스트케이스 수
		
		for(int t=1; t<=tc; t++) {
			answer = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());		// 고객의 수
			
			company = new int[2];		// 회사의 좌표
			home = new int[2];		// 집의 좌표
			
			st = new StringTokenizer(br.readLine());
			
			// 회사의 좌표 입력받기
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			
			// 집의 좌표 입력받기
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			// 고객의 좌표 입력받기
			customer = new int[n][2]; 
			for(int i=0; i<n; i++) {
				customer[i][0] = Integer.parseInt(st.nextToken());
				customer[i][1] = Integer.parseInt(st.nextToken());
			}
			
			visit = new int[n];
			arr = new int[n];
			
			dfs(0);
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
			
		}
		
		System.out.print(sb);
	}
	
	/*
	 * 순열 생성
	 * cnt = 재귀 호출 횟수
	 */
	public static void dfs(int cnt) {
		if(cnt == n) {		// 모든 고객을 다 넣음
			answer = Math.min(getDistance(), answer);	// 거리 계산
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(visit[i] == 0) {		// 방문 안함
				visit[i] = 1;
				arr[cnt] = i;
				dfs(cnt + 1);
				visit[i] = 0;
			}
		}
	}
	
	/*
	 * 거리 계산
	 */
	public static int getDistance() {
		/*
		 * 첫 번째 고객과 회사 사이의 거리
		 */
		int result = Math.abs(customer[arr[0]][0] - company[0]) + Math.abs(customer[arr[0]][1] - company[1]);
		
		/*
		 * 고객과 고객 사이의 거리들의 합
		 */
		for(int i=1; i<n; i++) {
			int a = arr[i-1];
			int b = arr[i];
			result += Math.abs(customer[a][0] - customer[b][0]) + Math.abs(customer[a][1] - customer[b][1]);
		}
		
		/*
		 * 마지막 고객과 집 사이의 거리
		 */
		result += Math.abs(customer[arr[n-1]][0] - home[0]) + Math.abs(customer[arr[n-1]][1] - home[1]);
		return result;
	}

}
