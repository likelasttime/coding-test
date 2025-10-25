import java.util.*;
import java.io.*;

/*
 * 최대 M원까지의 비용을 부담해준다.
 * 찬민이는 인천에서 LA까지 M원 이하로 사용하면서 도착할 수 있는 가장 빠른 길을 차선책으로 선택한다.
 * 인천은 언제나 1번 도시고, LA가 N번 도시다.
 */
public class Main {
	static class Node implements Comparable<Node> {
		int to;		// 도착지
		int val;		// 비용
		int time;		// 시간
		
		Node(int to, int val, int time) {
			this.to = to;
			this.val = val;
			this.time = time;
		}
		
		public int compareTo(Node node) {
			if(this.time != node.time) {
				return this.time - node.time;		// 시간이 적게 걸리는 순으로
			}
			return this.val - node.val;		// 비용이 적은 순으로
		}
	}
	
	final static int INF = (int)1e9;
	
	static List<Node>[] graph; 		// 인접 리스트
	static int N;
	static int M;		// 총 지원 비용
	static int[][] minTime;
	
	public static void dijkstra() {
		PriorityQueue<Node> que = new PriorityQueue();
		minTime[0][1] = 0;
		que.offer(new Node(1, 0, 0));		// 인천에서 출발
		
		while(!que.isEmpty()) {
			Node cur = que.poll();
			if(cur.to == N) {		// 도착했을 때
				return;
			}
			for(Node next : graph[cur.to]) {
				int time = cur.time + next.time;		// 거리 
				int cost = cur.val + next.val;			// 비용
				if(cost > M) {		// 최대 지원금을 넘어가면
					continue;
				}
				if(minTime[cost][next.to] > time) {		// 최단 거리가 맞으면
					for(int j=cost+1; j<=M; j++) {		// 최대 지원금 이하일때까지
						if(minTime[j][next.to] <= time) {
							break;
						}
						minTime[j][next.to] = time;		// 지원금 j로 다음 목적지까지 가는 최단 거리 갱신
					}
					minTime[cost][next.to] = time;
					que.offer(new Node(next.to, cost, time));
				}
			}
		}
	}
			
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());		// 테스트 케이스의 수
        
        for(int tc=1; tc<=TC; tc++) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());		// 2 <= 공항의 수 <= 100
        	M = Integer.parseInt(st.nextToken()); 		// 0 <= 총 지원 비용 <= 10,000
        	int K = Integer.parseInt(st.nextToken()); 		// 0 <= 티켓 정보의 수 <= 10,000
        	graph = new ArrayList[N + 1];		// 1부터 시작
        	minTime = new int[M + 1][N + 1];
        	// 초기화
        	for(int i=1; i<=N; i++) {
        		graph[i] = new ArrayList();
        	}
        	for(int i=1; i<=M; i++) {
        		Arrays.fill(minTime[i], INF);
        	}
        	
        	for(int k=0; k<K; k++) {
        		st = new StringTokenizer(br.readLine());
        		int U = Integer.parseInt(st.nextToken());		// 출발 공항
        		int V = Integer.parseInt(st.nextToken()); 		// 도착 공항
        		int C = Integer.parseInt(st.nextToken()); 		// 1 <= 비용 <= M
        		int D = Integer.parseInt(st.nextToken());		// 1 <= 소요 시간 <= 1,000
        		graph[U].add(new Node(V, C, D));
        	}
        	
        	// 그래프 정렬
        	for(int i=1; i<=N; i++) {
        		Collections.sort(graph[i]);
        	}
        	
        	dijkstra();
        	
        	int min = INF;
        	for(int i=1; i<=M; i++) {
        		min = Math.min(min, minTime[i][N]);
        	}
        	System.out.println(min == INF ? "Poor KCM" : min);
        }
    }
}
