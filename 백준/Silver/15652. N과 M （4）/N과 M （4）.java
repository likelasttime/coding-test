import java.util.*;

class Main {
	// 1 <= M <= N <= 8
	static int N;
	static int M;
	static StringBuilder sb;
	
	public static void dfs(int cnt, List<Integer> lst) {
		if(cnt == M) {
			// 수열은 사전 순으로 증가하는 순서로 출력
			for(int i : lst) {
				sb.append(i + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i=1; i<=N; i++) {
			// 길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.
			if(!lst.isEmpty() && lst.get(lst.size() - 1) > i) {
				continue;
			}
			lst.add(i);
			dfs(cnt + 1, lst);
			lst.remove(lst.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	
		M = sc.nextInt();
		sb = new StringBuilder();
		
		// 1부터 N까지의 자연수 중에서 중복 없이 M개를 고른 수열
		dfs(0, new ArrayList());
		System.out.print(sb);
	}
}