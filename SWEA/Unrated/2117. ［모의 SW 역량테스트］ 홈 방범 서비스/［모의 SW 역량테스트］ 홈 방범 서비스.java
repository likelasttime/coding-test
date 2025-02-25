import java.io.*;
import java.util.StringTokenizer;

class Solution {
    static int n;       // 5 <= 도시의 크기 <= 20
    static int m;       // 1 <= 집이 지불할 수 있는 비용 <= 10
    static int[][] city;

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    /*
        마름모 윗 부분 탐색
        x, y: 센터 좌표
     */
    public static int topRhombus(int x, int y, int k) {
        int row = 0;
        int cntHouse = 0;
        for(int depth=k-1; depth>=0; depth--) {
            for(int col=y-row; col<=y+row; col++) {
                if(!isValid(x - depth, col)) {      // 범위를 벗어난다면
                    continue;
                }
                if(city[x - depth][col] == 1) {     // 집 발견
                    cntHouse++;
                }
            }
            row++;
        }
        return cntHouse;
    }

    /*
        마름모 아래 부분 탐색
        x, y: 센터 좌표
     */
    public static int bottomRhombus(int x, int y, int k) {
        int row = k - 2;
        int cntHouse = 0;
        for(int depth=1; depth<k; depth++) {
            for(int col=y-row; col<=y+row; col++) {
                if(!isValid(x + depth, col)) {      // 범위를 벗어난다면
                    continue;
                }
                if(city[x + depth][col] == 1) {     // 집 발견
                    cntHouse++;
                }
            }
            row--;
        }
        return cntHouse;
    }

    /*
        운영 비용 반환
     */
    public static int getOperationPay(int k) {
        return k * k + (k - 1) * (k -1);
    }

    public static int simulation() {
        int answer = 0;
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                for(int k=1; k<=21; k++) {
                    int topCnt = topRhombus(x, y, k);
                    int bottomCnt = bottomRhombus(x, y, k);
                    int benefit = (topCnt + bottomCnt) * m - getOperationPay(k);
                    if(benefit >= 0) {
                        answer = Math.max(answer, topCnt + bottomCnt);
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());        // 총 테스트 케이스의 개수

        for(int tc=1; tc<=t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // 도시 정보 입력 받기(1: 집, 0: 빈곳)
            city = new int[n][n];
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    city[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + tc + " " + simulation());
        }
    }
}