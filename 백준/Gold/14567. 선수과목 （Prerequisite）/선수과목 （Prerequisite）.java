import java.io.*;
import java.util.*;

public class Main {
	static int N;		// 1 <= 과목의 수 <= 1,000
	static int M;		// 0 <= 선수 조건의 수 <= 500,000
	static List<Integer>[] lecture;		// [과목 번호 A] = {A 과목을 먼저 들어야하는 과목들}
	static int[] arr;		// 진입차수
	static int[] answer;		// 최소 몇 학기에 이수할 수 있는지
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lecture = new ArrayList[N + 1];
        arr = new int[N + 1];
        answer = new int[N + 1];
        
        // 초기화
        Arrays.fill(answer, 1);
        for(int i=1; i<=N; i++) {
        	lecture[i] = new ArrayList();
        }
        
        for(int i=0; i<M; i++) {
        	// A번 과목이 B의 선수과목
        	st = new StringTokenizer(br.readLine());
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	lecture[A].add(B);
        	arr[B]++;
        }
        
        Queue<Integer> que = new LinkedList();
        // 선수과목이 없는 과목을 찾아서 큐에 넣기
        for(int i=1; i<=N; i++) {
        	if(arr[i] == 0) {
        		que.offer(i);
        	}
        }
        
        while(!que.isEmpty()) {
        	int cur = que.poll();
        	for(int next : lecture[cur]) {
        		if(arr[next] == 0) {		// 이미 수강했으면
        			continue;
        		}
        		answer[next] = answer[cur] + 1;
        		arr[next]--;
        		if(arr[next] == 0) {
        			que.offer(next);
        		}
        	}
        }
        
        // 1번 과목부터 N번 과목까지 차례대로 최소 몇 학기에 이수할 수 있을지 출력
        for(int i=1; i<=N; i++) {
        	System.out.print(answer[i] + " ");
        }
    }
}
