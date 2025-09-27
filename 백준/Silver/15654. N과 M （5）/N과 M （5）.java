import java.util.*;

class Main {
	final static int SIZE = (int)1e4;
	
	// 1 <= M <= N <= 8
	static int N;
	static int M;
	static StringBuilder sb;
	static boolean[] visit;
	static int[] arr;
	
	public static void dfs(int cnt, List<Integer> lst) {
		if(cnt == M) {
			// 수열은 사전 순으로 증가하는 순서로 출력
			for(int i : lst) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			if(visit[arr[i]]) {
				continue;
			}
			visit[arr[i]] = true;
			lst.add(i);
			dfs(cnt + 1, lst);
			lst.remove(lst.size() - 1);
			visit[arr[i]] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	
		M = sc.nextInt();
		sb = new StringBuilder();
		visit = new boolean[SIZE + 1];
		arr = new int[N];
		
		// N개의 수 입력받기
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 오름차순 정렬
		Arrays.sort(arr);
		
		// 1부터 N까지의 자연수 중에서 중복 없이 M개를 고른 수열
		dfs(0, new ArrayList());
		System.out.print(sb);
	}
}