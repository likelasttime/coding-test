import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        // T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int size = sc.nextInt();		// 찾을 회문의 길이
            char[][] arr = new char[8][8];
            int answer = 0;

            // 글자판에 글자 입력하기
            for(int i=0; i<8; i++) {
                String str = sc.next();
                for(int j=0; j<8; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }

            // 가로 탐색
            for(int i=0; i<8; i++) {
                for(int j=0; j<=8 - size; j++) {
                    if(arr[i][j] == arr[i][j + size - 1]) {		// 첫 문자와 끝 문자가 같을 때
                        if(isPalindromeForCol(arr, i, j + 1, j + size - 2)) {
                            answer++;
                        }
                    }
                }
            }
            
            // 세로 탐색
            for(int i=0; i<8; i++) {
                for(int j=0; j<=8 - size; j++) {
                    if(arr[j][i] == arr[j + size - 1][i]) {		// 첫 문자와 끝 문자가 같을 때
                        if(isPalindromeForRow(arr, i, j + 1, j + size - 2)) {
                            answer++;
                        }
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + answer);
        }
    }
	
    /*
    	가로 탐색할 때 사용하는 팰린드롬 검사
    */
    public static boolean isPalindromeForCol(char[][] arr, int row, int left, int right) {
        while(left < right) {
            if(arr[row][left] != arr[row][right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /*
    	세로 탐색할 때 사용하는 팰린드롬 검사
    */
    public static boolean isPalindromeForRow(char[][] arr, int col, int top, int bottom) {
        while(top < bottom) {
            if(arr[top][col] != arr[bottom][col]) {
                return false;
            }
            top++;
            bottom--;
        }
        return true;
    }
}