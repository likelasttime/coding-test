import java.io.*;
import java.util.stream.Stream;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] firstInput = br.readLine().split(" ");			// 개미 수와 지름을 공백으로 구분해서 입력받기
		int N = Integer.parseInt(firstInput[0]);		// 개미 수
		int D = Integer.parseInt(firstInput[1]);		// 만들어야 하는 지름 길이
		int[] P = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();			// 개미의 좌표
		
		Arrays.sort(P);			// 오름차순 정렬
		
		System.out.println(twoPointer(P, D));		// 투포인터 실행 후 제거해야 하는 최소 개미 수 출력
	}
	
	public static int twoPointer(int[] P, int D) {		// P: 개미의 좌표, D: 지름
		int left = 0;					// 배열에서 왼쪽 인덱스
		int right = 0;				// 배열에서 오른쪽 인덱스
		int result = 0;				// 개미 수
		
		while(left < P.length && right < P.length) {			// 왼쪽 인덱스와 오른쪽 인덱스가 개미 좌표 배열 P의 길이를 넘지 않을 동안 반복
			if (P[right] - P[left] <= D) {		// 지름이 D 이하
				int tmp = right - left + 1;		// 개미 수
				if (result < tmp) {						// 개미 집합 안에 있는 개미 수를 갱신
					result = tmp;
				}
				right++;											// 지름이 D 이하기 때문에 오른쪽 인덱스를 증가시킴
			} else {
				left++;												// 지름을 줄여야 하니까 왼쪽 인덱스를 증가시킴
			}
		}
		
		return P.length - result;					// 제거해야 하는 최소 개미 수 반환
	}
	
}