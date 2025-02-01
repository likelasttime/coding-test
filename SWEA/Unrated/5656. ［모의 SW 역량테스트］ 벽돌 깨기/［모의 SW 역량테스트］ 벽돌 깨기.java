import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    static int n;
    static int w;
    static int h;
    static int[][] arr;
    static int answer;
    static int[] permutation;
    static boolean[] visit;

    static class Position {
        int x;
        int y;
        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    public static void bfs(Position start, int[][] copiedArr, boolean[][] visit) {
        Queue<Position> que = new LinkedList();
        que.add(new Position(start.x, start.y));
        while(!que.isEmpty()) {
            Position cur = que.poll();
            visit[cur.x][cur.y] = true;
            int distance = copiedArr[cur.x][cur.y] - 1;
            if(distance == 0) {
                continue;
            }
            for(int d=0; d<4; d++) {
                for(int wide=1; wide<=distance; wide++) {
                    int nx = (wide * DX[d]) + cur.x;
                    int ny = (wide * DY[d]) + cur.y;
                    // 인덱스 범위가 넘어가거나 이미 터질 범위로 체크했거나 벽돌이 없다면
                    if (!isValid(nx, ny) || visit[nx][ny] || copiedArr[nx][ny] == 0) {
                        continue;
                    }
                    que.add(new Position(nx, ny));
                }
            }
        }
    }

    public static void getAnswer(int[][] copiedArr) {
        int cnt = 0;
        for(int row=0; row<h; row++) {
            for(int col=0; col<w; col++) {
                if(copiedArr[row][col] > 0) {
                    cnt++;
                }
            }
        }
        answer = Math.min(answer, cnt);
    }

    public static Position getTopBrick(int col, int[][] copiedArr) {
        int row = -1;
        for(int i=0; i<h; i++) {
            if(copiedArr[i][col] > 0) {     // 벽돌이 있으면
                row = i;
                break;
            }
        }
        return new Position(row, col);
    }

    public static int[][] copy() {
        int[][] copied = new int[h][w];
        for(int x=0; x<h; x++) {
            for(int y=0; y<w; y++) {
                copied[x][y] = arr[x][y];
            }
        }
        return copied;
    }

    /*
        구슬을 떨어뜨릴 위치 순열 구하기
     */
    /*public static void dfs(int depth) {
        if(depth == n) {
            // 구슬을 n번 떨어뜨리기
            boolean[][] visit = new boolean[h][w];
            int[][] copiedArr = copy();
            for(int i=0; i<n; i++) {
                int idx = permutation.get(i);
                Position position = getTopBrick(idx, copiedArr);
                if(position.x == -1) {
                    continue;
                }
                bfs(position, copiedArr, visit);
                copiedArr = drop(visit, copiedArr);
                visit = new boolean[h][w];
            }
            getAnswer(copiedArr);
            return;
        }
        for(int i=0; i<w; i++) {        // 가로 인덱스
            permutation.add(i);
            dfs(depth + 1);
            permutation.remove(depth);
        }
    }*/

    public static int[][] drop(boolean[][] visit, int[][] copiedArr) {
        int[][] newArr = new int[h][w];
        for(int col=0; col<w; col++) {      // 열
            int idx = h - 1;
            for(int row=0; row<h; row++) {      // 행
                if(!visit[h - 1 - row][col]) {
                    newArr[idx--][col] = copiedArr[h - 1 - row][col];
                }
            }
        }
        return newArr;
    }

    public static void play() {
        for(int first=0; first<w; first++) {        // 첫 번째 구슬 위치
            permutation[0] = first;
            for(int second=0; second<w; second++) {     // 두 번째 구슬 위치
                permutation[1] = second;
                for(int third=0; third<w; third++) {        // 세 번째 구슬 위치
                    permutation[2] = third;
                    for(int fourth=0; fourth<w; fourth++) {     // 네 번째 구슬 위치
                        permutation[3] = fourth;
                        // 구슬을 n번 떨어뜨리기
                        boolean[][] visit = new boolean[h][w];
                        int[][] copiedArr = copy();
                        for(int i=0; i<n; i++) {
                            int idx = permutation[i];
                            Position position = getTopBrick(idx, copiedArr);
                            if(position.x == -1) {
                                continue;
                            }
                            bfs(position, copiedArr, visit);
                            copiedArr = drop(visit, copiedArr);
                            visit = new boolean[h][w];
                        }
                        getAnswer(copiedArr);
                        if(answer == 0) {
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());        // 테스트 케이스 수
        StringTokenizer st;
        
        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());       // 1 <= 구슬을 쏠 횟수 <= 4
            w = Integer.parseInt(st.nextToken());   // 2 <= 가로 크기 <= 12
            h = Integer.parseInt(st.nextToken());       // 2 <= 세로 크기 <= 15
            visit = new boolean[w];
            answer = Integer.MAX_VALUE;
            arr = new int[h][w];
            permutation = new int[4];
            
            // 벽돌들의 정보 입력 받기
            for(int row=0; row<h; row++) {
                st = new StringTokenizer(br.readLine());
                for(int col=0; col<w; col++) {
                    arr[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            
            play();
            System.out.println("#" + tc + " " + answer);
        }
    }
}