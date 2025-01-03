import java.io.*;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    final static int[] DX = {-1, 1, 0, 0, -1, 1, 1, -1};
    final static int[] DY = {0, 0, -1, 1, 1, 1, -1, -1};

    static int n;
    static char[][] arr;
    static int clickCnt;
    static boolean[][] visit;

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static boolean isNearBomb(int x, int y) {
        int cnt = 0;
        for(int d=0; d<8; d++) {
            int nx = x + DX[d];
            int ny = y + DY[d];
            if(!isValid(nx, ny)) {
                continue;
            }
            if(arr[nx][ny] == '*') {        // 지뢰
                cnt++;
            }
        }
        if(cnt == 0) {
            return false;
        }
        return true;
    }

    public static void bfs(int x, int y) {
        Queue<int[]> que = new LinkedList();
        que.offer(new int[]{x, y});
        visit[x][y] = true;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            if(arr[cur[0]][cur[1]] == '*' || isNearBomb(cur[0], cur[1])) {
                continue;
            }
            for(int d=0; d<8; d++) {
                int nx = cur[0] + DX[d];
                int ny = cur[1] + DY[d];
                if(!isValid(nx, ny) || visit[nx][ny] || arr[nx][ny] == '*') {
                    continue;
                }
                visit[nx][ny] = true;
                que.offer(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());        // 전체 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new char[n][n];
            for(int i=0; i<n; i++) {
                arr[i] = br.readLine().toCharArray();
            }
            visit = new boolean[n][n];
            clickCnt = 0;
            // 주위에 지뢰가 없는 곳부터 클릭하기
            for(int x=0; x<n; x++) {
                for(int y=0; y<n; y++) {
                    if(arr[x][y] == '*' || visit[x][y]) {  // 지뢰라면
                        continue;
                    }
                    if(!isNearBomb(x, y)) {
                        bfs(x, y);
                        clickCnt++;
                    }
                }
            }
            // 남은 곳 클릭하기
            for(int x=0; x<n; x++) {
                for(int y=0; y<n; y++) {
                    if(arr[x][y] != '*' && !visit[x][y]) {
                        clickCnt++;
                    }
                }
            }
            System.out.println("#" + tc + " " + clickCnt);
        }
    }
}