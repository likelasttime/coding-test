import java.io.*;
import java.util.*;

public class Main {
    // 상우하좌
    final static int[] DX = {-1, 0, 1, 0};
    final static int[] DY = {0, 1, 0, -1};

    static Deque<int[]> que;
    static int d;
    static int[][] arr;
    static int answer;
    static int n;       // 2 <= 보드의 크기 <= 100

    public static boolean isValid(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    public static boolean move() {
        int[] head = que.peek();
        int nx = head[0] + DX[d];
        int ny = head[1] + DY[d];
        if(!isValid(nx, ny) || arr[nx][ny] == 1) {      // 벽이거나 자기 자신의 몸과 부딪히면
            System.out.print(++answer);
            return true;
        } else if(arr[nx][ny] == 2) {       // 이동한 칸에 사과가 있으면
            arr[nx][ny] = 1;     // 사과가 없어짐
        } else {
            // 몸 길이 줄이기
            int[] tail = que.pollLast();
            arr[tail[0]][tail[1]] = 0;

        }
        // 뱀의 머리 추가
        arr[nx][ny] = 1;
        que.offerFirst(new int[]{nx, ny});
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());        // 0 <= 사과의 개수 <= 100
        arr = new int[n + 1][n + 1];
        answer = 0;
        que = new ArrayDeque();

        // 사과의 위치 입력받기
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
        }

        arr[1][1] = 1;      // 초기 뱀 위치
        que.offer(new int[]{1, 1});

        int l = Integer.parseInt(br.readLine());    // 뱀의 방향 변환 횟수
        d = 1;      // 초기에 오른쪽 방향

        // 뱀의 방향 변환 정보 입력받기
        int[] x = new int[l];
        String[] c = new String[l];
        for(int i=0; i<l; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            c[i] = st.nextToken();
        }
        int idx = 0;
        while(true) {
            if(idx < l && answer == x[idx]) {
                // 방향 전환
                if(c[idx].equals("L")) {
                    d = (d - 1 + 4) % 4;
                } else {
                    d = (d + 1) % 4;
                }
                idx++;
            }

            if(move()) {
                return;
            }
            answer++;
        }
    }
}