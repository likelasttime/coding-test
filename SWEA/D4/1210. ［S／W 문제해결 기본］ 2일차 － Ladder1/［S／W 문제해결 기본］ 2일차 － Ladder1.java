import java.util.Scanner;

class Solution
{
    final static int SIZE = 100;

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int test_num = sc.nextInt(); // 테스트 케이스의 번호
            int answer = 0; // 도착하게 되는 출발점의 x 좌표
            int[][] arr = new int[SIZE][SIZE];
            boolean[][] visit;

            for(int x=0; x<SIZE; x++) {
                for(int y=0; y<SIZE; y++) {
                    arr[x][y] = sc.nextInt();
                }
            }

            for(int i=SIZE-1; i>0; i--) {        // 시작점
                if (arr[0][i] != 1) {    // 출발점이 아니라면
                    continue;
                }
                int x = 0;        // x좌표
                int y = i;        // y좌표
                boolean flag = false;
                visit = new boolean[SIZE][SIZE];

                while (true) {
                    int nx = x;
                    int ny = y;

                    visit[nx][ny] = true;

                    if (arr[nx][ny] == 2) {        // 도착
                        flag = true;
                    }

                    // 왼쪽으로 갈 수 있다면
                    if (!isNotValid(nx, ny - 1) && arr[nx][ny - 1] == 1 && !visit[nx][ny - 1]) {
                        y--;
                    } else if (!isNotValid(nx, ny + 1) && arr[nx][ny + 1] == 1 && !visit[nx][ny + 1]) {    // 오른쪽으로 갈 수 있다면
                        y++;
                    } else if (!isNotValid(nx + 1, ny)) {    // 아래로 내려가기
                        x++;
                    } else {    // 인덱스 범위를 초과하면
                        break;
                    }
                }

                if (flag) {
                    answer = i;        // 시작점 저장
                    break;
                }
            }
            System.out.println("#" + test_num + " " + answer);
        }
    }

    public static boolean isNotValid(int x, int y) {
        return x < 0 || x >= SIZE || y < 0 || y >= SIZE;	// 인덱스가 유효하지 않다면
    }
}