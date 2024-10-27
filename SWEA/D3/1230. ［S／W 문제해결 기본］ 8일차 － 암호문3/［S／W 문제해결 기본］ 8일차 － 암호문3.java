import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();		// 암호문 개수
            List<Integer> lst = new ArrayList();	// 원본 암호문 뭉치
            for(int i=0; i<n; i++) {
            	lst.add(sc.nextInt());
            }
            int cmdCnt = sc.nextInt();		// 명령어의 개수
			while(cmdCnt-- > 0) {
            	String cmd = sc.next();
                if(cmd.equals("I")) {		// 삽입
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    while(y-- > 0) {
                    	lst.add(x++, sc.nextInt());
                    }
                } else if(cmd.equals("D")) {	// 삭제
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    while(y-- > 0) {
                    	lst.remove(x);
                    }
                } else {		// 추가
                    int y = sc.nextInt();
                    while(y-- > 0 ) {
                    	lst.add(sc.nextInt());
                    }
                }
            }
            
            System.out.print("#" + test_case + " ");
            for(int i=0; i<10; i++) {
            	System.out.print(lst.get(i) + " ");
            }
            System.out.println();
		}
	}
}