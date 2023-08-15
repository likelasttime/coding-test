import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{
	static int white;		// 흰 색종이 갯수
	static int blue;		// 파란색 색종이 갯수
	static int N;		// 한 변의 길이
	static int[][] arr;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());		// 한 변의 길이
		arr = new int[N][N];
		
		/** 색종이 배열 입력 받기 */
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0, N);		// 1<<N은 2^N과 같다.
            
		System.out.println(white);		// 하얀색 색종이 출력
		System.out.println(blue);		// 파란색 색종이 출력
		
            
	}
	
	/*
	 * x = 현재 행
	 * y = 현재 열
	 * num = 배열에 들어갈 값, z의 시작위치 값
	 * size = 배열의 길이
	 */
	public static void dfs(int x, int y, int num, int size) {
		// 시작 위치 행 또는 열이 N을 넘거나 종료 위치 행 또는 열이 N을 넘기면 리턴
		if(N < x || N < y || x+size > N || y+size > N) {
			return;
		}
		
		int color = arr[x][y];		// 시작 컬러
		boolean flag = false;
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(arr[i][j] != color) {		// 모두 같으면 return
					flag = true;
					break;
				}
			}
			if(flag) {		// 다른 색깔 발견
				break;
			}
		}
		
		if(!flag) {		// 모든 색깔이 같음. 더이상 나누지 않는다.
			if(color == 1) {		// 파란색
				blue++;
			}else {
				white++;		// 하얀색
			}
			return;
		}
		
		dfs(x, y, num, size/2); // 좌측상단
		dfs(x, y+size/2, num + size * size / 4, size/2); // 우측상단
		dfs(x+size/2, y, num + size * size / 4 * 2, size/2); // 좌측하단
		dfs(x+size/2, y+size/2, num + size * size / 4 * 3, size/2); // 우측하단
	}

}