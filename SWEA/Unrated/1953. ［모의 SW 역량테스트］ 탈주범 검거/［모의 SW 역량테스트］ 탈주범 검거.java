import java.io.*;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    // 상우하좌
    final static int[] DY = {-1, 0, 1, 0};
    final static int[] DX = {0, 1, 0, -1};

    static int n;       // 지하 터널 지도의 세로 크기
    static int m;       // 지하 터널 지도의 가로 크기
    static int r;       // 맨홀 뚜껑이 위치한 장소의 세로 위치
    static int c;       // 맨홀 뚜껑이 위치한 장소의 가로 위치
    static int l;       // 탈출 후 소요된 시간
    static int[][] matrix;      // 지하 터널 지도
    static boolean[][] tunnel = {{false, false, false, false},
            {true, true, true, true},       // 상우하좌
            {true, false, true, false},     // 상하
            {false, true, false, true},     // 좌우
            {true, true, false, false},     // 상우
            {false, true, true, false},     // 하우
            {false, false, true, true},     // 하좌
            {true, false, false, true}      // 상좌
    };

    static class Position {
        int y;      // 세로
        int x;      // 가로
        Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    /*
        y: 세로
        x: 가로
     */
    public static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }


    /*
        탈주범이 위치할 수 있는 장소의 개수 반환
     */
    public static int bfs() {
        int answer = 1;
        Queue<Position> que = new ArrayDeque();
        boolean[][] visit = new boolean[n][m];
        que.offer(new Position(r, c));      // 맨홀 뚜껑의 좌표를 큐에 추가
        visit[r][c] = true;
        int time = 1;
        while(!que.isEmpty()) {
            int cnt = que.size();
            for(int depth=0; depth<cnt; depth++) {      // 한 시간에 얼만큼 움직일 수 있는지 탐색
                Position curPos = que.poll();
                int tunnelNum = matrix[curPos.y][curPos.x];     // 터널 구조물 번호
                for (int d = 0; d < 4; d++) {
                    if (!tunnel[tunnelNum][d]) {
                        continue;
                    }
                    int ny = curPos.y + DY[d];
                    int nx = curPos.x + DX[d];
                    // 지도의 범위를 벗어나거나 터널이 없는 장소이거나 이미 방문한 장소라면
                    if (!isValid(ny, nx) || matrix[ny][nx] == 0 || visit[ny][nx]) {
                        continue;
                    }
                    // 터널 사이가 연결되어있지 않다면
                    if(!tunnel[matrix[ny][nx]][(d + 2) % 4]) {
                        continue;
                    }
                    visit[ny][nx] = true;
                    que.offer(new Position(ny, nx));
                    answer++;
                }
            }
            time++;
            if(time == l) {
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 테스트 케이스의 개수
        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            int answer = 1;
            // 지하 터널 지도 정보 입력 받기
            matrix = new int[n][m];
            for(int row=0; row<n; row++) {
                st = new StringTokenizer(br.readLine());
                for(int col=0; col<m; col++) {
                    matrix[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            if(l > 1) {
                answer = bfs();
            }
            bw.write("#" + tc + " " + answer + "\n");
        }
        bw.flush();
    }
}