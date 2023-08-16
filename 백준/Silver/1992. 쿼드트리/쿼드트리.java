import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main
{
	static int n;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());		// 영상의 크기
		arr = new int[n][n];
		
		/** 영상 입력 받기 */
		for(int i=0; i<n; i++) {
			String str = br.readLine();		// 한줄 입력받기
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
			}
		}
		
		dfs(0, 0, n);		
            
		System.out.print(sb);			
	}
	
	/*
	 * x = 현재 행
	 * y = 현재 열
	 * size = 배열의 길이
	 */
	public static void dfs(int x, int y, int size) {
		// 시작 위치 행 또는 열이 n을 넘거나 종료 위치 행 또는 열이 n을 넘기면 리턴
		if(n < x || n < y || x+size > n || y+size > n) {
			return;
		}
		
		boolean flag = false;
		int initColor = arr[x][y];		// 좌측 상단 컬러
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(arr[i][j] != initColor) {		// 다른 색깔 발견
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		
		if(!flag) {            // 범위 내의 색깔이 같다
			sb.append(initColor);
			return;        // 더이상 쪼개지 않아도 됨
		}
		
		sb.append("(");
		dfs(x, y, size/2); // 좌측상단
		dfs(x, y+size/2, size/2); // 우측상단
		dfs(x+size/2, y, size/2); // 좌측하단
		dfs(x+size/2, y+size/2, size/2); // 우측하단
		sb.append(")");
	}

}