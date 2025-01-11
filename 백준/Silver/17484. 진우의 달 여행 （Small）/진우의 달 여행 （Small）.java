import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Main {
    static class Node {
        int x;
        int y;
        int d;
        int sum;
        Node(int x, int y, int d, int sum) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.sum = sum;
        }
    }

    static int n;
    static int m;
    static int[][] arr;
    static int answer;

    final static int[] DX = {1, 1, 1};
    final static int[] DY = {-1, 0, 1};

    public static void bfs() {
        Queue<Node> que = new LinkedList();
        for(int i=0; i<m; i++) {
            que.add(new Node(0, i, -1, arr[0][i]));
        }
        while(!que.isEmpty()) {
            Node cur = que.poll();
            if(answer <= cur.sum) {
                continue;
            }
            if(cur.x == n-1) {
                answer = Math.min(answer, cur.sum);
                continue;
            }
            for(int dir=0; dir<3; dir++) {
                int nx = cur.x + DX[dir];
                int ny = cur.y + DY[dir];
                if(0 > nx || nx >= n || 0 > ny || ny >= m) {
                    continue;
                }
                if(dir == cur.d) {
                    continue;
                }
                que.offer(new Node(nx, ny, dir, cur.sum + arr[nx][ny]));
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        answer = Integer.MAX_VALUE;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        bfs();
        System.out.println(answer);
    }
}