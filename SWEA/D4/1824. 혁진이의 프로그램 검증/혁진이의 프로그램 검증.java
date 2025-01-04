import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    static int r;
    static int c;
    static String[] arr;
    static String answer;
    static boolean[][][][] visit;

    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    static class Node {
        int x;
        int y;
        int d;
        int memory;
        Node(int x, int y, int d, int memory) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.memory = memory;
        }
    }

    public static void bfs() {
        Queue<Node> que = new LinkedList();
        if(Character.isDigit(arr[0].charAt(0))) {
            que.offer(new Node(0, 0, 3, arr[0].charAt(0) - '0'));
        } else {
            que.offer(new Node(0, 0, 3, 0));
        }

        while(!que.isEmpty()) {
            Node cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            int d = cur.d;
            int memory = cur.memory;
            char ch = arr[x].charAt(y);

            if(ch == '@') {
                answer = "YES";
                return;
            }

            if(visit[x][y][d][memory]) {
                continue;
            }
            visit[x][y][d][memory] = true;

            if(ch == '?') {
                for(int dir=0; dir<4; dir++) {
                    int nx = DX[dir] + x;
                    int ny = DY[dir] + y;
                    if(nx < 0) {
                        nx = r - 1;
                    }
                    if(ny < 0) {
                        ny = c - 1;
                    }
                    if(nx >= r) {
                        nx = 0;
                    }
                    if(ny >= c) {
                        ny = 0;
                    }
                    que.offer(new Node(nx, ny, dir, cur.memory));
                }
            } else {
                if (ch == '<') {
                    d = 2;
                } else if (ch == '>') {
                    d = 3;
                } else if (ch == '^') {
                    d = 0;
                } else if (ch == 'v') {
                    d = 1;
                } else if (ch == '_') {
                    if (memory == 0) {
                        d = 3;
                    } else {
                        d = 2;
                    }
                } else if (ch == '|') {
                    if (memory == 0) {
                        d = 1;
                    } else {
                        d = 0;
                    }
                } else if (ch == '+') {
                    if(memory == 15) {
                        memory = 0;
                    } else {
                        memory++;
                    }
                } else if (ch == '-') {
                    if (memory == 0) {
                        memory = 15;
                    } else {
                        memory--;
                    }
                } else if (Character.isDigit(ch)) {
                    memory = ch - '0';
                }
            }

            x = x + DX[d];
            y = y + DY[d];
            if(x < 0) {
                x = r - 1;
            }
            if(y < 0) {
                y = c - 1;
            }
            if(x >= r) {
                x = 0;
            }
            if(y >= c) {
                y = 0;
            }
            que.offer(new Node(x, y, d, memory));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());        // 전체 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++) {
            String[] input = br.readLine().split(" ");
            r = Integer.parseInt(input[0]);
            c = Integer.parseInt(input[1]);
            visit = new boolean[r][c][4][16];
            arr = new String[r];
            answer = "NO";
            for(int i=0; i<r; i++) {
                arr[i] = br.readLine();
            }
            bfs();
            System.out.println("#" + tc + " " + answer);
        }
    }
}