import java.util.Scanner;

class Main
{
	static int r;		// 행
	static int c;		// 열
	static int answer;		// r행 c열을 방문한 순서
	static int N;		// 지수
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();		// 지수
		r = sc.nextInt();		// 행
		c = sc.nextInt();		// 열
		
		dfs(0, 0, 0, 1<<N);		// 1<<N은 2^N과 같다.
            
		System.out.println(answer);
            
	}
	
	/*
	 * x = 현재 행
	 * y = 현재 열
	 * num = 배열에 들어갈 값, z의 시작위치 값
	 * size = 배열의 길이
	 */
	public static void dfs(int x, int y, int num, int size) {
		// 시작 위치가 r, c를 넘거나 종료 위치가 r, c를 넘기면 리턴
		if(r < x || c < y || x+size <= r || c+size <= c) {
			return;
		}
		
		if(r == x && c == y) {	// 입력으로 주어졌던 행, 열 위치에 도착함
			answer = num;		
			return;
		}
		
		dfs(x, y, num, size/2); // 좌측상단
		dfs(x, y+size/2, num + size * size / 4, size/2); // 우측상단
		dfs(x+size/2, y, num + size * size / 4 * 2, size/2); // 좌측하단
		dfs(x+size/2, y+size/2, num + size * size / 4 * 3, size/2); // 우측하단
	}

}