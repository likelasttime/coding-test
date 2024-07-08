import java.io.*;
import java.util.stream.Stream;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Iterator;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;		// 소개팅을 받지 못한 두 사람의 점수의 합
		int n = Integer.parseInt(br.readLine());		// 지인 수
		int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Set set = new HashSet<Integer>();
		for (int i=0; i<n; i++) {
			if (set.contains(Math.abs(arr[i]))) {			// n점, -n점이 있으면 set에서 해당 점수를 제거
				set.remove(Math.abs(arr[i]));
			} else{
				set.add(arr[i]);
			}
		}
		
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			answer += (Integer) iterator.next();
		}
		System.out.print(answer);		// 소개팅을 받지 못한 두 사람의 점수의 합을 출력
	}
}