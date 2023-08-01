import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(bf.readLine());		// 스위치 개수
		int[] arr = new int[num+1];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		/** 스위치의 상태를 입력 받기 */
		for(int i=1; i<=num; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int stu_num = Integer.parseInt(bf.readLine());		// 학생 수
		
		/** 학생 수만큼 학생의 성별과 수를 입력 받기 */
		int sex, n;
		int left, right;
		for(int i=0; i<stu_num; i++) {		
			st = new StringTokenizer(bf.readLine());		// 한 줄 입력 받기
			sex = Integer.parseInt(st.nextToken());			// 성별
			n = Integer.parseInt(st.nextToken());			// 수
			
			if(sex == 1) {	// 남자
				for(int j=n; j<=num; j+=n) {
					arr[j] ^= 1;		// XOR 연산
				}
			}else {		// 여자
				left = n-1;
				right = n+1;
				arr[n] ^= 1;		// XOR 연산
				while(true) {
					if(0 >= left || right > num) {		// 범위 초과
						break;
					}
					if(arr[left] != arr[right]) {
						break;
					}
					arr[left] ^= 1;		// XOR 연산
					arr[right] ^= 1;		// XOR 연산
					
					left -= 1;
					right += 1;
				}
			}
		}
		
		/** 스위치의 모든 상태 출력 */
		for(int i=1; i<=num; i++) {
            if(i > 1 && (i-1) % 20 == 0) {
				System.out.println();
			}
			System.out.print(arr[i] + " ");
		}
		
	}

}
