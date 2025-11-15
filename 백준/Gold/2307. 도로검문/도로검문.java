import java.io.*;
import java.util.*;

/*
	도로는 양방향
	도로검문을 통하여 얻을 수 있는 탈출의 최대 지연시간을 구하기
	용의자가 도시에 진입하는 지점은 항상 1번이다.
	최종적으로 도달해야하는 지점은 항상 N번이다.
	경찰이 어떤 도로를 막으면 용의자는 탈출 못할 수도 있고, -1을 출력해야 한다.
	지연 효과가 없으면 0을 출력
 */

public class Main {
    final static int INF = Integer.MAX_VALUE;
    
    static int N;    // 6 <= 지점의 수 <= 1,000
    static int M;    // 6 <= 도로의 수 <= 5,000
    static List<Node>[] lst;    // 인접 연결 리스트
    static int[] prev;          // 경로 추적을 위한 배열
    
    static class Node implements Comparable<Node> {
        int to;    // 도시 번호
        int time;
        
        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.time - node.time;    // 시간 오름차순
        }
    }

    // 다익스트라 알고리즘: 경로 추적
    public static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] timeArr = new int[N + 1];
        Arrays.fill(timeArr, INF);
        pq.offer(new Node(1, 0));
        timeArr[1] = 0;
        prev = new int[N + 1]; // 경로 추적 배열
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.time > timeArr[cur.to]) continue;
            
            for(Node next : lst[cur.to]) {
                int nextTime = timeArr[cur.to] + next.time;
                if(timeArr[next.to] <= nextTime) continue;
                timeArr[next.to] = nextTime;
                prev[next.to] = cur.to;  // 경로 추적
                pq.offer(new Node(next.to, nextTime));
            }
        }
        return timeArr[N];
    }

    // 경찰이 막을 도로 계산
    public static Set<String> findBlockedEdges(int targetNode) {
        Set<String> blockedEdges = new HashSet<>();
        int currentNode = targetNode;
        while (currentNode != 1) {
            int previousNode = prev[currentNode];
            // 이전 노드와 현재 노드 간의 간선을 blockedEdges에 추가
            blockedEdges.add(Math.min(currentNode, previousNode) + " " + Math.max(currentNode, previousNode));
            currentNode = previousNode;
        }
        return blockedEdges;
    }

    // 경찰이 막을 도로들을 제외하고 다익스트라 실행
    public static int dijkstraWithBlockedEdges(Set<String> blockedEdges) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] timeArr = new int[N + 1];
        Arrays.fill(timeArr, INF);
        pq.offer(new Node(1, 0));
        timeArr[1] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.time > timeArr[cur.to]) continue;

            for(Node next : lst[cur.to]) {
                // 경찰이 막은 도로는 건너뛰기
                if (blockedEdges.contains(Math.min(cur.to, next.to) + " " + Math.max(cur.to, next.to))) {
                    continue;
                }
                
                int nextTime = timeArr[cur.to] + next.time;
                if(timeArr[next.to] <= nextTime) continue;
                timeArr[next.to] = nextTime;
                pq.offer(new Node(next.to, nextTime));
            }
        }
        return timeArr[N];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lst = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            lst[i] = new ArrayList<>();
        }

        // 도로 정보 입력
        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());  // 1 <= 통과시간 <= 10,000
            lst[a].add(new Node(b, t));
            lst[b].add(new Node(a, t));
        }

        // 1번 도시에서 N번 도시까지의 최단시간 계산
        int shortestTime = dijkstra();
        
        // 경찰이 막을 도로들을 계산
        Set<String> blockedEdges = findBlockedEdges(N);

        // 경찰이 막은 도로를 제외한 상태로 다익스트라를 여러 번 실행
        int maxDelay = -1;
        for (String blockedEdge : blockedEdges) {
            Set<String> singleBlockedEdge = new HashSet<>();
            singleBlockedEdge.add(blockedEdge);
            int result = dijkstraWithBlockedEdges(singleBlockedEdge);
            if (result == INF) {
                System.out.println(-1);
                return;
            }
            maxDelay = Math.max(maxDelay, result);
        }

        // 지연 효과가 없다면 0을 출력
        if (maxDelay == shortestTime) {
            System.out.println(0);
        } else {
            System.out.println(maxDelay - shortestTime);
        }
    }
}
