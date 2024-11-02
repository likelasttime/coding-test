import java.util.Scanner;

class Solution
{
    static int[] arr1;
    static int[] arr2;
    static boolean[] visit;
    static int answer = 0;

    final static int SIZE = 100;

    public static void dfs(int cur) {
        if(cur == 99) {
            answer = 1;
            return;
        }
        if(arr1[cur] != 0 && !visit[arr1[cur]]) {
            visit[arr1[cur]] = true;
            dfs(arr1[cur]);
            visit[arr1[cur]] = false;
        }
        if(arr2[cur] != 0 && !visit[arr2[cur]]) {
            visit[arr2[cur]] = true;
            dfs(arr2[cur]);
            visit[arr2[cur]] = false;
        }
    }

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int test_num = sc.nextInt(); 	// 테스트 케이스 번호
            int pathCnt = sc.nextInt();		// 길의 총 개수
            arr1 = new int[SIZE];
            arr2 = new int[SIZE];
            visit = new boolean[SIZE];
            // 순서쌍 입력받기
            for(int i=0; i<pathCnt; i++) {
                int a = sc.nextInt(); 	// 출발 노드
                int b = sc.nextInt();	// 도착 노드
                if(arr1[a] == 0) {
                    arr1[a] = b;
                } else {
                    arr2[a] = b;
                }
            }

            visit[0] = true;
            answer = 0;
            dfs(0);
            System.out.println("#" + test_num + " " + answer);
        }
    }
}