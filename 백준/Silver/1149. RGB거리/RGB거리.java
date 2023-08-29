import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [아이디어]
 * 현재 행의 위쪽 행에서 다른 열에 있는 두 값 중에서 최소값을 고른다. 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());		// 집의 수
		int[] arr = new int[3];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int red = r + Math.min(arr[1], arr[2]);
			int green = g + Math.min(arr[0], arr[2]);
			int blue = b + Math.min(arr[0], arr[1]);
			
			arr[0] = red;
			arr[1] = green;
			arr[2] = blue;
		}
		
		/*
		 * N번 집 행에서 가장 최솟값 찾기
		 */
		int answer = arr[0];
		for(int i=0; i<2; i++) {
			if(answer > arr[i+1]) {
				answer = arr[i+1];
			}
		}
		
		System.out.println(answer);		// 모든 집을 칠하는 비용의 최솟값

	}

}
