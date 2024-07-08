import java.io.*;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Stream;
import java.util.Iterator;
import java.util.Arrays;

class Main {
	
	static Set set = new HashSet();			// 3의 배수가 되는 날로부터 2일 이내의 날들을 중복없이 저장하기 위해 사용
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);		// 집의 개수
		int m = Integer.parseInt(input[1]);		// 장마 기간
		int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();		// 마을의 땅 높이
		int[] water = new int[n];		// 물의 높이
		
		for (int i=1; i<=m; i++) {
			int[] input2 = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();		// 시작 위치, 종료 위치
			rain(input2[0], input2[1], water);			// 장마가 내린다
			if (i % 3 == 0) {			// 현재 날짜가 3의 배수라면 이틀 전 상태로 돌아가기
				getOutWater(water);			// 배수 시스템 작동
			}
		}
		
		/*
			장마가 지난 뒤, 마을의 모든 땅 높이는 그 땅에 쌓인 물의 높이만큼 증가
		*/
		for (int i=0; i<n; i++) {
			arr[i] += water[i];
			System.out.print(arr[i] + " ");
		}
	}
	
	/*
		start번째에서 end번째 집까지 비가 내린다.
	*/
	public static void rain (int start, int end, int[] water) {
		for (int i=start-1; i<end; i++) {				// 배열은 0에서 시작해서 -1을 해준다
			water[i]++;
			set.add(i);
		}
	}
	
	/*
		배수 시스템 작동
	*/
	public static void getOutWater(int[] water) {
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			int idx = (int) iterator.next();
			water[idx]--;			// 물의 높이가 1 줄어든다
		}
		set.clear();				// 집합을 초기화
	}
}