import java.util.*;

class Main {
	final static int SIZE = (int)1e4;
	
	// 1 <= M <= N <= 8
	static int N;
	static int M;
	static StringBuilder sb;
	static boolean[] visit;
	static int[] arr;
	static Set<String> set = new HashSet<>();

	public static void dfs(int cnt, List<Integer> lst) {
	    if(cnt == M) {
	        String s = lst.toString(); // 수열 문자열로 변환
	        if(!set.contains(s)) {
	            set.add(s);
	            for(int i : lst) sb.append(i + " ");
	            sb.append("\n");
	        }
	        return;
	    }
	    for(int i=0; i<N; i++) {
	    	if(visit[i]) {
	    		continue;
	    	}
	    	visit[i] = true;
	        lst.add(arr[i]);
	        dfs(cnt + 1, lst);
	        visit[i] = false;
	        lst.remove(lst.size() - 1);
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
		
		dfs(0, new ArrayList());
		System.out.print(sb);
	}
}