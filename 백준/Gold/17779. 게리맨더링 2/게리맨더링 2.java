import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] A;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 모든 (x,y,d1,d2) 탐색
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {
                        if (x + d1 + d2 > N) continue;
                        if (y - d1 < 1 || y + d2 > N) continue;

                        solve(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void solve(int x, int y, int d1, int d2) {
        int[][] district = new int[N+1][N+1];

        // 경계선 표시
        for (int i = 0; i <= d1; i++) {
            district[x+i][y-i] = 5;
            district[x+d2+i][y+d2-i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            district[x+i][y+i] = 5;
            district[x+d1+i][y-d1+i] = 5;
        }

        // 5번 구역 안쪽 채우기
        for (int r = x+1; r < x+d1+d2; r++) {
            boolean fill = false;
            for (int c = 1; c <= N; c++) {
                if (district[r][c] == 5) fill = !fill;
                if (fill) district[r][c] = 5;
            }
        }

        int[] people = new int[6];

        // 1번 선거구
        for (int r = 1; r < x+d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (district[r][c] == 0) {
                    people[1] += A[r][c];
                }
            }
        }
        // 2번 선거구
        for (int r = 1; r <= x+d2; r++) {
            for (int c = y+1; c <= N; c++) {
                if (district[r][c] == 0) {
                    people[2] += A[r][c];
                }
            }
        }
        // 3번 선거구
        for (int r = x+d1; r <= N; r++) {
            for (int c = 1; c < y-d1+d2; c++) {
                if (district[r][c] == 0) {
                    people[3] += A[r][c];
                }
            }
        }
        // 4번 선거구
        for (int r = x+d2+1; r <= N; r++) {
            for (int c = y-d1+d2; c <= N; c++) {
                if (district[r][c] == 0) {
                    people[4] += A[r][c];
                }
            }
        }
        // 5번 선거구
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (district[r][c] == 5) {
                    people[5] += A[r][c];
                }
            }
        }

        // 최대 - 최소 차이 계산
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= 5; i++) {
            max = Math.max(max, people[i]);
            min = Math.min(min, people[i]);
        }

        answer = Math.min(answer, max - min);
    }
}
