import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{	
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());		// 탑의 수
		st = new StringTokenizer(br.readLine());
		int[][] answer = new int[n][3];		// 0열:n번째 탑, 1열:탑의 높이, 2열:입력받은 값
		answer[0][2] = Integer.parseInt(st.nextToken());
		int result = 0;
		
		sb.append("0 ");			// 첫 번재 탑은 무조건 0을 출력
		
		for(int i=1; i<n; i++) {
			answer[i][2] = Integer.parseInt(st.nextToken());		// 높이 입력 받기
			if(answer[i-1][2] > answer[i][2]) {		// 이전 탑의 높이와 비교
				sb.append(i).append(" ");
				answer[i][0] = i;		// 탑의 번호
				answer[i][1] = answer[i-1][2];		// 탑의 높이
			}else {
				answer[i][1] = answer[i][2];
				for(int j=i-1; j>=0; j--) {
					if(answer[j][1] > answer[i][2]) {
						answer[i][0] = answer[j][0];		// 탑의 번호
						answer[i][1] = answer[j][1];		// 탑의 높이
						break;
					}else{
                        j = answer[j][0];
                    }
				}
				sb.append(answer[i][0]).append(" ");
			}
		}
		
		System.out.print(sb);
	}	
}