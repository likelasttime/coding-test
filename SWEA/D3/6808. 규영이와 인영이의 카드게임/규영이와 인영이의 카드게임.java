import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{	
	static int[] visit;
	static int[] arr = new int[9];		// 규영이가 낸 9장의 카드
	static int[] cards = new int[9];	// 인영이가 낸 9장의 카드
	static int win = 0;
	static int lose = 0;

	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());		// 테스트케이스 수
			
		for(int t=1; t<=tc; t++) {
			st = new StringTokenizer(br.readLine());
			visit = new int[19];
			cards = new int[9];
			win = 0;
			lose = 0;
			for(int i=0; i<9; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				visit[arr[i]] = -1; 
			}
			
			perm(0);
			sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
			
		}
		
		System.out.print(sb);
		
	}	
	
	public static void perm(int cnt) {
		if(cnt == 9) {		// 인영이가 9장의 카드를 다 고름
			int gyuTotal = 0;		// 규영이의 점수
			int yeanTotal = 0;		// 인영이의 점수
			for(int i=0; i<9; i++) {	// 9라운드까지 진행
				if(cards[i] < arr[i]) {		// 인영 < 규영
					gyuTotal += cards[i] + arr[i];		// 규영이가 두 카드에 적힌 수의 합만큼 점수를 얻는다.
				}else if(cards[i] > arr[i]) {
					yeanTotal += cards[i] + arr[i];		// 인영이가 두 카드에 적힌 수의 합만큼 점수를 얻는다.
				}
			}
			if(gyuTotal > yeanTotal) {
				win++;
			}else if(gyuTotal < yeanTotal) {
				lose++;
			}
			return;
		}
		
		for(int i=1; i<=18; i++) {
			if(visit[i] == 1 || visit[i] == -1) {	// 규영이가 고른 카드이거나 인영이가 고른 카드라면
				continue;
			}
			cards[cnt] = i;
			visit[i] = 1;
			perm(cnt+1);
			visit[i] = 0;
			cards[cnt] = 0;
		}
	}
}