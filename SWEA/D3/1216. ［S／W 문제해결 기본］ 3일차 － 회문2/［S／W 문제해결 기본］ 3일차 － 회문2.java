import java.util.Scanner;

class Solution
{	
    final static int SIZE = 100;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
		//T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int test_num = sc.nextInt();
            char[][] arr = new char[SIZE][SIZE];
            int answer = 1;		// 팰린드롬의 최대 길이
            
            // 문자 배열에 문자 입력하기
            for(int i=0; i<SIZE; i++) {
                String str = sc.next();
            	for(int j=0; j<SIZE; j++) {
                	arr[i][j] = str.charAt(j);
                }
            }
            
            // 가로 탐색
            for(int x=0; x<SIZE; x++) {
                for(int len=2; len<=SIZE; len++) {		// 문자열의 길이
                    for(int y=0; y<=SIZE-len; y++) {
						if(arr[x][y] == arr[x][y + len - 1]) {		// 첫 문자와 끝 문자가 같으면
                        	if(palindromeCol(arr, x, y + 1, y + len - 2)) {
                            	answer = Math.max(answer, len);
                                break;
                            }
                        }
                    }
                }
            }
            
             // 세로 탐색
            for(int x=0; x<SIZE; x++) {
                for(int len=2; len<=SIZE; len++) {		// 문자열의 길이
                    for(int y=0; y<=SIZE-len; y++) {
						if(arr[y][x] == arr[y + len - 1][x]) {		// 첫 문자와 끝 문자가 같으면
                        	if(palindromeRow(arr, x, y + 1, y + len - 2)) {
                            	answer = Math.max(answer, len);
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println("#" + test_num + " " + answer);   
		}
	}
    
    /*
    	가로 방향으로 팰린드롬 탐색
    */
    public static boolean palindromeCol(char[][] arr, int x, int left, int right) {
    	while(left < right) {
        	if(arr[x][left] != arr[x][right]) {
            	return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /*
    	세로 방향으로 팰린드롬 탐색
    */
    public static boolean palindromeRow(char[][] arr, int y, int top, int bottom) {
    	while(top < bottom) {
        	if(arr[top][y] != arr[bottom][y]) {
            	return false;
            }
            top++;
            bottom--;
        }
        return true;
    }
}