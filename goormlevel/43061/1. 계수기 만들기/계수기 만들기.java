import java.io.*;
import java.util.stream.Stream;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());			// 숫자판의 개수
		int[] a = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();		// 각 숫자판의 최댓값을 저장하는 배열
		int[] b = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();		// 각 숫자판의 초기값을 저장하는 배열
		int k = Integer.parseInt(br.readLine());		// 버튼을 누르는 횟수
		
		/*
			k번 버튼을 누른다.
		*/
		for (int i=0; i<k; i++) {
			dfs(a, b, n-1);
		}
		
		/*
			n개의 숫자를 공백없이 출력
		*/
		for (int i=0; i<n; i++) {
			System.out.print(b[i]);
		}
	}
	
	/*
		각 자리가 최댓값 이상이 되면 왼쪽으로 올림을 전파해야 해서 재귀로 간단하게 구현
		a: 각 숫자판의 최댓값을 저장하는 배열
		b: 각 숫자판의 초기값을 저장하는 배열
	*/
	public static void dfs (int[] a, int[] b, int idx) {
		b[idx]++;				// idx 자리에 있는 값 1 증가
    if (b[idx] <= a[idx]) {				// 기저조건(해당 자리에 있는 값이 최댓값을 넘기지 않아서 재귀 종료)
    	return;
    } else {			// 올림이 발생했을 경우
    	b[idx] = 0;			// idx 자리에 있는 값을 0으로 초기화
      if (idx-1 < 0) {			// 맨 앞자리가 최댓값을 넘기면 더이상 왼쪽으로 올림을 전파하지 않음
				return;
			}
      dfs(a, b, idx-1);			// idx의 왼쪽 자리를 1 올림하기 위한 재귀 호출
    }
  }
}