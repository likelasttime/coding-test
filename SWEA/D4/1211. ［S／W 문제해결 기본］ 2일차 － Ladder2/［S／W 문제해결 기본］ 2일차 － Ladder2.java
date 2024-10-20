import java.util.Scanner;

class Solution
{
    final static int MAX_ROW = 100;
    final static int MAX_COL = 100;

    static int[][] arr;
    static boolean[][] visit;

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int test_num = sc.nextInt();		// 현재 테스트 케이스
            arr = new int[MAX_ROW][MAX_COL];
            visit = new boolean[MAX_ROW][MAX_COL];
            int answer = 0;

            for(int i=0; i<MAX_ROW; i++) {
                for(int j=0; j<MAX_COL; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int dist = Integer.MAX_VALUE;
            for(int i=MAX_COL-1; i>=0; i--) {
                if(arr[0][i] == 1)	{		// 출발점이라면
                    visit = new boolean[MAX_ROW][MAX_COL];
                    int curDist = play(0, i);
                    if(curDist < dist) {
                    	dist = curDist;
                        answer = i;
                    }
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    public static int play(int x, int y) {
        int cnt = 0;		// 이동거리
        while(true) {
            // 바닥에 도착
            if(x == MAX_ROW) {
                break;
            }

            visit[x][y] = true;

            if(y-1 >= 0 && arr[x][y - 1] == 1 && !visit[x][y - 1]) {		// 왼쪽으로 갈 수 있다면
                y--;
            } else if(y + 1 < MAX_COL && arr[x][y + 1] == 1 && !visit[x][y + 1]) {		// 오른쪽으로 갈 수 있다면
                y++;
            } else {		// 아래로 내려가기
                x++;
            }

            cnt++;
        }
        return cnt;
    }
}