import java.util.Scanner;

public class Main {

	static int t;		// 요리 시간 초
	static int aCount = 0;		// a 버튼을 누른 횟수
	static int bCount = 0;		// b 버튼을 누른 횟수
	static int cCount = 0;		// c 버튼을 누른 횟수
	static int totalClick = 1000;		// a, b, c 버튼을 누른 횟수 합
	static boolean flag = false;		// t초를 단 한번이라도 만들었는지
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		// 입력 받기 위해서 선언 및 할당
		StringBuilder sb = new StringBuilder();		// 출력 효율을 높이기 위해 선언 및 할당
		
		t = sc.nextInt();	// 요리 시간 초
		
		dfs(0, 0, 0);		// DFS 호출
		
		if(flag) {				// t초를 맞춤
			sb.append(aCount).append(" ").append(bCount).append(" ").append(cCount);	// 버튼 별 클릭 횟수를 추가
			System.out.print(sb);		// 정답 출력
		}else {			// t초를 못 맞춘 경우
			System.out.print(-1);		// -1을 출력함
		}
	}
	
	/*
	 * a = a 버튼을 누른 횟수 
	 * b = b 버튼을 누른 횟수
	 * c = c 버튼을 누른 횟수
	 */
	public static void dfs(int a, int b, int c) {
		
		if(t < a * 300 + b * 60 + 10 * c) {		// t초를 초과하면
			return;			// 뒤로 돌아가기
		}
		
		if(t == a * 300 + b * 60 + 10 * c) {		// t초와 같아지면
			flag = true;			// t초를 한번이라도 맞추었다.
			if(totalClick <= a+b+c) {		// 최소 버튼 조작 횟수가 아님
				return;				// 뒤로 돌아가기
			}
			totalClick = a+b+c;		// 총 클릿 횟수 갱신
			aCount = a;				// a 버튼 클릭 횟수 갱신
			bCount = b;				// b 버튼 클릭 횟수 갱신
			cCount = c;				// c 버튼 클릭 횟수 갱신
			return;			// 뒤로 돌아가기
		}
		
		dfs(a+1, b, c);		// a 버튼 클릭 횟수 증가
		dfs(a, b+1, c);		// b 버튼 클릭 횟수 증가
		dfs(a, b, c+1);		// c 버튼 클릭 횟수 증가
	}

}
