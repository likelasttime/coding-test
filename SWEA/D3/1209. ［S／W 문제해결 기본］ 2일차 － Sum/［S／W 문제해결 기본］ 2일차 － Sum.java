import java.util.Scanner;

class Solution
{
    static final int MAX_ROW = 100;
    static final int MAX_COL = 100;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		//T=sc.nextInt();

		for(int test_case = 1; test_case <= 10; test_case++)
		{
			int test_num = sc.nextInt();		// 테스트케이스 번호
            int[][] arr = new int[MAX_ROW][MAX_COL];
            int answer = 0;
            
            // 배열에 값 입력받기
            for(int i=0; i<MAX_ROW; i++) {
            	for(int j=0; j<MAX_COL; j++) {
                	arr[i][j] = sc.nextInt();
                }
            }
            
            // 행의 합 구하기
            for(int x=0; x<MAX_COL; x++) {
                int total = 0;
            	for(int y=0; y<MAX_ROW; y++) {
                	total += arr[y][x];
                }
                answer = Math.max(total, answer);
            }
            
            // 열의 합 구하기
            for(int x=0; x<MAX_ROW; x++) {
                int total = 0;
            	for(int y=0; y<MAX_COL; y++) {
                	total += arr[x][y];
                }
                answer = Math.max(answer, total);
            }
            
            // 왼쪽 대각선 합 구하기
            int leftTotal = 0;
            int rightTotal = 0;
            for(int x=0; x<MAX_ROW; x++) {
            	leftTotal += arr[x][x];
                rightTotal += arr[MAX_ROW - x - 1][MAX_COL - x - 1];
            }
            
            answer = Math.max(answer, leftTotal);
            answer = Math.max(answer, rightTotal);
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
}