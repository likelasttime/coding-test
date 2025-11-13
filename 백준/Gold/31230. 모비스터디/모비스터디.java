import java.util.*;
import java.io.*;

/*
 * 1번부터 N번까지 번호가 부여된 N개의 도시
 * 서로 다른 두 도시를 잇는 M개의 양방향 도로
 * 도로마다 통행하는 데 걸리는 시간이 양의 정수로 존재함
 * 각 도시에서 도로를 통해 항상 다른 모든 도시로 이동
 * 같은 도시를 출발지와 도착지로 두고 있는 도로는 존재하지 않는다.
 * 임의의 두 도시를 잇는 도로는 최대 1개다.
 * 민경이는 A번 도시
 * 시은이는 B번 도시
 * A -> B로 가는 최단 경로 위에 존재하는 도시로 약속 장소를 정한다.
 * A번 도시, B번 도시도 약속 장소가 될 수 있다.
 * 약속 장소로 정할 수 있는 도시의 개수를 출력
 */
class Main {
    static int N;        // 2 <= 도시의 개수 <= 200,000
    static int M;        // 1 <= 도로의 개수 <= 300,000
    static int A;        // 1 <= 민경이가 살고 있는 도시의 번호 <= N
    static int B;        // 1 <= 시은이가 살고 있는 도시의 번호 <= N
    static List<City>[] lst;        // 양방향 인접 리스트
    
    static class City implements Comparable<City> {
        int to;        // 도착지
        long time;        // 시간
        
        City(int to, long time) {
            this.to = to;
            this.time = time;
        }
        
        @Override
        public int compareTo(City city) {
            return Long.compare(this.time, city.time);
        }
    }
    
    // 다익스트라 알고리즘
    public static long[] dijkstra(int start, int[] prev) {
        long[] timeArr = new long[N + 1];
        Arrays.fill(timeArr, Long.MAX_VALUE);
        timeArr[start] = 0;
        
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.offer(new City(start, 0));
        
        while(!pq.isEmpty()) {
            City cur = pq.poll();
            
            if(timeArr[cur.to] < cur.time) {
                continue;
            }
            
            for(City next : lst[cur.to]) {
                long nextTime = next.time + cur.time;
                if(timeArr[next.to] > nextTime) {
                    timeArr[next.to] = nextTime;
                    prev[next.to] = cur.to;  // 이전 도시에 대한 정보 기록
                    pq.offer(new City(next.to, nextTime));
                }
            }
        }
        return timeArr;
    }
    
    // A -> B로 가는 최단 경로 위의 모든 도시를 추적
    public static List<Integer> getPath(int[] prev, int destination) {
        List<Integer> path = new ArrayList<>();
        for (int i = destination; i != -1; i = prev[i]) {
            path.add(i);
        }
        Collections.reverse(path);  // 역추적하였으므로 경로를 뒤집어야 함
        return path;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        // 각 도로의 정보 입력받기
        lst = new ArrayList[N + 1];
        for(int i=1; i<=N; i++) {
            lst[i] = new ArrayList<>();
        }
        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());     // 1 <= 시간 <= 10^9
            // 양방향 연결
            lst[a].add(new City(b, c));
            lst[b].add(new City(a, c));
        }
        
        // A -> 모든 도시, B -> 모든 도시
        int[] prevFromA = new int[N + 1];
        int[] prevFromB = new int[N + 1];
        Arrays.fill(prevFromA, -1);
        Arrays.fill(prevFromB, -1);
        
        long[] timeFromA = dijkstra(A, prevFromA);
        long[] timeFromB = dijkstra(B, prevFromB);
        
        // A -> B로 가는 최단 경로 시간
        long shortestTime = timeFromA[B];
        
        // 약속 장소 후보 도시 개수
        int count = 0;
        
        // A -> B 최단 경로 위에 있는 도시들을 추적
        List<Integer> answer = new ArrayList();
        for(int i = 1; i <= N; i++) {
            if(timeFromA[i] + timeFromB[i] == shortestTime) {
                count++;
                answer.add(i);
            }
        }
        
        Collections.sort(answer);
        System.out.println(count);
        for(int i : answer) {
        	// 최단 경로 위에 존재하는 도시들을 출력
        	System.out.print(i + " ");
        }
    }
}
