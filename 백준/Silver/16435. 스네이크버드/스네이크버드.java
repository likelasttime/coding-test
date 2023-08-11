import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main
{	
	static StringBuilder sb = new StringBuilder();
	static int n;	// 과일의 개수
	static int l;	// 스네이크버드의 초기 길이 정수
	static int[] arr;
	static int answer;
	
	public static void main(String args[]) throws Exception
	{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());	// 과일의 개수
		l = Integer.parseInt(st.nextToken());	// 스네이크버드의 초기 길이 정수
		arr = new int[n];
		answer = l;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); 		// 오름차순 정렬
		
		for(int i=0; i<n; i++) {
			if(arr[i] > answer) {
				continue;
			}
			answer++;		// 먹은 과일의 갯수
		}
				
		sb.append(answer).append("\n");	
		System.out.println(sb);		// 스네이크 버드의 최대 길이 출력
		
	}	
}