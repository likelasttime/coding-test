import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main
{	
	static StringBuilder sb = new StringBuilder();
	static int n;	// 배달하는 설탕의 킬로그램
	static int answer = -1;	// 상근이가 배달하는 봉지의 최소 개수
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
        /** 큰 봉지를 우선으로 사용한다. */
		boolean flag = false;
		for(int i=n/5; i>=0; i--) {
			for(int j=n/3; j>=0; j--) {
				if(i*5 + j*3 == n) {
					answer = i + j;
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
				
		sb.append(answer).append("\n");	// 참가자들의 상금 합
		System.out.println(sb);
		
	}	
}