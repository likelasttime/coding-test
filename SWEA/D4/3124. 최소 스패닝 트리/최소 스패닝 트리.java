import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// return this.weight - o.weight;
			return Integer.compare(this.weight, o.weight);
		}	
	}
	
	static Edge[] edgeList;
	static int V, E;
	static int[] parents;
	
	static void make() {
		parents = new int[V+1];
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;		// 싸이클 발생 의미로 해석됨
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
        int tc = Integer.parseInt(in.readLine());		// 테스트케이스 수
        
        for(int t=1; t<=tc; t++){
            st = new StringTokenizer(in.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            edgeList = new Edge[E];
            for(int i=0; i<E; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from, to, weight);
            }

            // 간선 리스트를 가중치 기준 오름차순 정렬
            Arrays.sort(edgeList);

            // V개의 정점으로 make set 작업
            make();

            long result = 0;		// MST 비용
            int count = 0;		// 연결된 간선 개수
            for(Edge edge : edgeList) {		// 비용이 작은 간선순으로 꺼내어서 처리
                if(union(edge.from, edge.to)) {
                    result += edge.weight;
                    if(++count == V-1) {
                        break;
                    }
                }
            }
            out.append("#").append(String.valueOf(t)).append(" ").append(String.valueOf(result)).append("\n");
        }
        out.flush();
    }

}
