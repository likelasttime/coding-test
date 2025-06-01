import java.util.*;
import java.io.*;

public class Solution {
    final static int[] DX = {-1, 1, 0, 0};
    final static int[] DY = {0, 0, -1, 1};

    static int n;       // 지도의 한 변 길이
    static int k;       // 최대 공사 가능 깊이
    static int answer;      // 최대 거리
    static boolean[][] visit;
    static int[][] arr;     // n * n 크기의 지도

    static class Mountain implements Comparable<Mountain> {
        int x;
        int y;
        int height;

        Mountain(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Mountain mountain) {
            return mountain.height - this.height;    // 내림차순
        }
    }

    public static boolean isValid(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < n;
    }

    public static void dfs(int x, int y, int distance, boolean isUseK) {
        answer = Math.max(answer, distance);
        for(int d=0; d<4; d++) {
            int nx = DX[d] + x;
            int ny = DY[d] + y;
            if(isValid(nx, ny) && !visit[nx][ny]) {
                if(arr[x][y] > arr[nx][ny]) {
                    visit[nx][ny] = true;
                    dfs(nx, ny, distance + 1, isUseK);
                    visit[nx][ny] = false;
                }
                if(!isUseK && arr[nx][ny] >= arr[x][y]) {
                    for (int depth = 1; depth <= k; depth++) {
                        if (arr[nx][ny] - depth < arr[x][y]) {
                            arr[nx][ny] -= depth;
                            visit[nx][ny] = true;
                            dfs(nx, ny, distance + 1, true);
                            visit[nx][ny] = false;
                            arr[nx][ny] += depth;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());    // 테스트 케이스의 개수
        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            answer = 0;     // 거리 초기화
            visit = new boolean[n][n];
            arr = new int[n][n];
            List<Mountain> mountains = new ArrayList();
            // 지도 정보 입력받기
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    mountains.add(new Mountain(i, j, arr[i][j]));
                }
            }
            Collections.sort(mountains);
            int maxHeight = mountains.get(0).height;
            for(int i=0; i<5; i++) {
                Mountain cur = mountains.get(i);
                if(cur.height != maxHeight) {
                    break;
                }
                visit[cur.x][cur.y] = true;
                dfs(cur.x, cur.y, 1, false);
                visit[cur.x][cur.y] = false;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}