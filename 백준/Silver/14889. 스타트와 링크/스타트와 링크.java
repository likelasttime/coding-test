import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 조합 문제
 * 스타트 팀의 팀원을 n/2개 선택하면, 링크 팀은 자동으로 스타트 팀이 고르지 않은 것을 고르면 된다.
 * 
 */

public class Main {

	static int n;		// 전체 인원 4 <= n <= 20
	static int[][] s;	// 입력으로 주어지는 두 팀의 능력치 배열
	static int answer;	// 스타트 팀과 링크 팀의 능력치의 차이의 최솟값
    static boolean[] visit;        // 방문 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());	// 전체 인원
		s = new int[n][n];		// 입력으로 주어지는 두 팀의 능력치 배열
		answer = 987654321;
        visit = new boolean[n];
		
		/*
		 * 능력치 입력 받기
		 * s[i][j]와 s[j][i]는 같지 않을 수도 있다고 함
		 */
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				s[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0);
		
		System.out.println(answer);		// 스타트 팀과 링크 팀의 능력치의 차이의 최솟값을 출력
		
	}
	
	/*
	 * 조합
	 * depth = 선택한 요소의 갯수
	 * visit = 방문 배열
	 */
	public static void dfs (int depth, int start) {
		if (depth == n/2) {
			getPower(visit);
			return;
		}
		
		for (int i=start; i<n; i++) {
			if (visit[i] == false) {
				visit[i] = true;	
				dfs(depth + 1, i + 1);
				visit[i] = false;
			}
		}
	}
	
	/*
	 * 능력치 구하기 - 순열
	 * start 팀이 선택하지 않는 것을 link 팀이 선택한다.
	 */
	public static void getPower (boolean[] visit) {
		int startTeam = 0;
		int linkTeam = 0;
		
		for (int i=0; i<n-1; i++) {
			for (int j=i+1; j<n; j++) {
				if (visit[i] && visit[j]) {	// start 팀에서 선택함
					startTeam += s[i][j] + s[j][i];
				} else if (!visit[i] && !visit[j]) {	// link 팀의 선택
					linkTeam += s[i][j] + s[j][i];
				}
			}
		}
		
		answer = Math.min(answer, Math.abs(startTeam - linkTeam));
		
		if (answer == 0) {		// 더이상 작은 값이 나올일이 없으니 바로 종료시킨다.
			System.out.println(0);
			System.exit(0);
		}
		
	}

}