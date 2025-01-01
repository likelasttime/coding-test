import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class Solution {
    static int n;
    static int[][] arr;
    static double e;
    static int[] parent;

    static class Node implements Comparable<Node> {
        int start;
        int end;
        double value;

        Node(int start, int end, double value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.value, o.value);
        }
    }

    public static void makeParent() {
        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
    }

    public static int find(int idx) {
        if(parent[idx] == idx) {
            return idx;
        }
        return parent[idx] = find(parent[idx]);
    }

    public static boolean union(int idx1, int idx2) {
        int parent1 = find(idx1);
        int parent2 = find(idx2);
        if(parent1 == parent2) {
            return false;
        }
        parent[parent2] = parent1;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 전체 테스트 케이스의 수
        for(int tc=1; tc<=t; tc++) {
            n = Integer.parseInt(br.readLine());        // 섬의 개수
            arr = new int[n][2];
            double answer = 0;
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<n; i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());       // x좌표
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                arr[i][1] = Integer.parseInt(st.nextToken());       // y좌표
            }

            e = Double.parseDouble(br.readLine());
            PriorityQueue<Node> pq = new PriorityQueue();
            for(int i=0; i<n-1; i++) {
                for(int j=i+1; j<n; j++) {
                    pq.add(new Node(i, j, e * (Math.pow(arr[i][0] - arr[j][0], 2) + Math.pow(arr[i][1] - arr[j][1], 2))));
                }
            }

            makeParent();

            int cnt = 0;
            while(!pq.isEmpty()) {
                Node cur = pq.poll();
                if(union(cur.start, cur.end)) {
                    answer += cur.value;
                }
                if(cnt == n) {
                    break;
                }
            }
            System.out.println("#" + tc + " " + Math.round(answer));
        }
    }
}