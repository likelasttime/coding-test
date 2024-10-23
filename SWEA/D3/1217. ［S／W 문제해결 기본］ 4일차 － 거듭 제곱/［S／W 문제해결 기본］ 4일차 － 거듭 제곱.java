import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
		//T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int test_num = sc.nextInt();
            int n = sc.nextInt();		// 밑
            int m = sc.nextInt();		// 지수
            
			System.out.println("#" + test_num + " " + pow(n, m));
		}
	}
    
    public static int pow(int n, int m) {
        if(m == 1) {
        	return n;
        }
    	return n * pow(n, m - 1);
    }
}