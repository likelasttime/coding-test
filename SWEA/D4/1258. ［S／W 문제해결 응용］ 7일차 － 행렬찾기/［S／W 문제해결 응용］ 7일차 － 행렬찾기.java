import java.util.*;

public class Solution {
    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    static int[][] arr;
    static boolean[][] visit;
    static int n;

    static class Node implements Comparable<Node> {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node node) {
            if(this.x * this.y != node.x * node.y) {
                return (this.x * this.y) - (node.x * node.y);       // 행렬의 크기가 작은 순
            }
            return this.x - node.x;
        }
    }

    static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    /*
        x, y: 시작점 좌표
     */
    public static int[] bfs(int x, int y) {
        Queue<Position> que = new ArrayDeque();
        que.offer(new Position(x, y));
        visit[x][y] = true;
        int[] result = new int[2];
        while(!que.isEmpty()) {
            Position cur = que.poll();
            result[0] = cur.x;
            result[1] = cur.y;
            for(int d=0; d<4; d++) {
                int nx = DX[d] + cur.x;
                int ny = DY[d] + cur.y;
                if(!isValid(nx, ny) || visit[nx][ny] || arr[nx][ny] == 0) {
                    continue;
                }
                visit[nx][ny] = true;
                que.offer(new Position(nx, ny));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();   // 테스트 케이스 갯수
        for(int tc=1; tc<=t; tc++) {
            n = sc.nextInt();
            arr = new int[n][n];
            visit = new boolean[n][n];
            int answer = 0;     // 부분 행렬들의 갯수
            List<Node> nodes = new ArrayList();
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(visit[i][j] || arr[i][j] == 0) {
                        continue;
                    }
                    int[] result = bfs(i, j);
                    nodes.add(new Node(result[0] - i + 1, result[1] - j + 1));
                    answer++;
                }
            }
            Collections.sort(nodes);
            System.out.print("#" + tc + " ");
            System.out.print(answer + " ");
            for(Node node : nodes) {
                System.out.print(node.x + " " + node.y + " ");
            }
            System.out.println();
        }
    }
}