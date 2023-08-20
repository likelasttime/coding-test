import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());		// 사과나무의 개수
		
		/*
		 * 갊자가 바라는 나무의 높이 n개를 입력받기
		 */
		int total = 0;		// 만들어야 하는 나무의 높이 총합
		int even = 0;		// 짝수의 개수
		int odd = 0;		// 홀수의 개수
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int height = Integer.parseInt(st.nextToken());
			even += height / 2;
			odd += height % 2;
			total += height;
		}
		
		if(total % 3 == 0 && even >= odd) {		// 모든 나무의 높이의 합이 3의 배수
			System.out.print("YES");
		}else {
			System.out.print("NO");
		}

	}
}
