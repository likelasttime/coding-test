import java.util.*;
import java.io.*;

public class Main {
    final static int CANNON_DISTANCE = 50;
    final static int CANNON_TIME = 2;
    final static int WALKING_TIME = 5;

    static int n;       // 0 <= 대포의 숫자 <= 100
    static Position start;     // 출발점
    static Position end;       // 도착점
    static List<Position> position;
    static List<Edge>[] edgeLst;    // 모든 정점을 연결한 간선 리스트 배열

    static class Position {
        double x;
        double y;
        boolean isCannon;       // 대포인가?

        Position(double x, double y, boolean isCannon) {
            this.x = x;
            this.y = y;
            this.isCannon = isCannon;
        }
    }

    static class Edge implements Comparable<Edge> {
        int id;     // 연결된 정점 번호
        double time;        // 시간(가중치)

        Edge(int id, double time) {
            this.id = id;
            this.time = time;
        }

        @Override
        public int compareTo(Edge edge) {
            return Double.compare(this.time, edge.time);
        }
    }

    public static double getEuclideanDistance(Position a, Position b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    /*
        대포를 이용했을 때 최단 시간 반환
     */
    public static double useCannon(double distance) {
        if(distance == CANNON_DISTANCE) {
            return CANNON_TIME;
        }
        // 남은 시간 계산
        // 남은 거리는 걸어서 가기
        double time = CANNON_TIME;
        double remainDistance = Math.abs(distance - CANNON_DISTANCE);
        time += remainDistance / WALKING_TIME;
        return time;
    }

    public static double getMinTime(Position start, Position end) {
        double distance = getEuclideanDistance(start, end);
        double time = distance / WALKING_TIME;
        if(!start.isCannon) {      // 현재 위치가 대포가 아니면
            // 걸어가기
            return time;
        }
        // 현재 위치가 대포라면, 걸어가는 것과 대포로 이동하는 것중 최솟값을 선택
        return Math.min(time, useCannon(distance));
    }

    /*
        모든 정점을 간선으로 연결
        간선의 가중치가 시간
     */
    public static void makeGraph() {
        int nodeCnt = position.size();
        for(int i=0; i<nodeCnt-1; i++) {
            for(int j=i+1; j<nodeCnt; j++) {
                Position start = position.get(i);
                Position end = position.get(j);
                // 양방향
                edgeLst[i].add(new Edge(j, getMinTime(start, end)));
                edgeLst[j].add(new Edge(i, getMinTime(end, start)));
            }
        }
    }

    public static double dijkstra() {
        // 시간, 지금 정점
        int nodeCnt = position.size();
        PriorityQueue<Edge> que = new PriorityQueue();
        double[] time = new double[nodeCnt];
        Arrays.fill(time, Double.MAX_VALUE);
        time[0] = 0;
        que.offer(new Edge(0, 0));

        while(!que.isEmpty()) {
            Edge cur = que.poll();
            if(time[cur.id] < cur.time) {
                continue;
            }
            for(Edge next : edgeLst[cur.id]) {
                double nextTime = next.time + cur.time;
                if(time[next.id] > nextTime) {
                    time[next.id] = nextTime;
                    que.offer(new Edge(next.id, nextTime));
                }
            }
        }
        return time[nodeCnt - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        position = new ArrayList();
        // 출발점 입력받기
        st = new StringTokenizer(br.readLine());
        start = new Position(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), false);
        position.add(start);

        // 도착점 입력받기
        st = new StringTokenizer(br.readLine());
        end = new Position(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), false);

        // 대포 입력받기
        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            position.add(new Position(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()), true));
        }

        // 도착점 추가
        position.add(end);

        edgeLst = new ArrayList[position.size()];
        for(int i=0; i< position.size(); i++) {
            edgeLst[i] = new ArrayList();
        }

        // 모든 간선을 연결하여 그래프 생성
        makeGraph();
        // 다익스트라
        System.out.print(dijkstra());
    }
}