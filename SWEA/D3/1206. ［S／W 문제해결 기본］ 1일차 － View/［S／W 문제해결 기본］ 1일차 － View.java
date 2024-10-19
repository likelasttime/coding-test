import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();		// 건물의 개수
            int[] arr = new int[n];
            int answer = 0;
            
            // 건물의 높이 입력 받기
            for(int i=0; i<n; i++) {
            	arr[i] = sc.nextInt();
            }
            
            for(int i=2; i<n-2; i++) {
            	int leftDiff1 = arr[i] - arr[i - 1];
                int leftDiff2 = arr[i] - arr[i - 2];
                int rightDiff1 = arr[i] - arr[i + 1];
                int rightDiff2 = arr[i] - arr[i + 2];
                
                if(leftDiff1 < 0 || rightDiff1 < 0 || rightDiff2 < 0 || leftDiff2 < 0) {	// 양쪽 모두 거리가 2이상 확보가 되지 않는다면
                	continue;
                }
                
                int left = Math.min(leftDiff1, leftDiff2);
                int right = Math.min(rightDiff1, rightDiff2);
                
                answer += Math.min(left, right);
            }
			
            System.out.println("#" + test_case + " " + answer);
		}
	}
}