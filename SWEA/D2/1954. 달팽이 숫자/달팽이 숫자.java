import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	static int[][] arr;
    static int n;
	static int nx, ny;
	/** 우하좌상 */
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();		// 테스트케이스 수
 
		for(int test_case = 1; test_case <= T; test_case++)
		{
            n = sc.nextInt();		// 배열의 크기
			arr = new int[n][n];
			arr[0][0] = 1;
            dfs(0, 2, 0, 0);
            
            System.out.printf("#%d\n", test_case);
            /** 정답 출력 */
            for(int i=0; i<n; i++) {
            	for(int j=0; j<n; j++) {
            		System.out.print(String.format("%d ", arr[i][j]));	// 각 원소는 공백으로 구분
            	}
            	System.out.println();
            }
		}
	}
    
    public static void dfs(int idx, int num, int x, int y){		// 방향 인덱스, 배열에 입력할 값, 현재 배열 x와 y 좌표
        if(num > n*n){		// 모든 숫자 입력함
        	return;
        }
        nx = dx[idx] + x;
        ny = dy[idx] + y;
        if(0 <= nx && nx < n && 0 <= ny && ny < n && arr[nx][ny] == 0){		
            arr[nx][ny] = num;
        	dfs(idx, num + 1, nx, ny);
        }else{		// // 같은 방향으로 가면 인덱스 초과 또는 이미 값이 있어서 다른 방향으로 전환
        	dfs((idx + 1) % 4, num, x, y);
        }
        
    }
}