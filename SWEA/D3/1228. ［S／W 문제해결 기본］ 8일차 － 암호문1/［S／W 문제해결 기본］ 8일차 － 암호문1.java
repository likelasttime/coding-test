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
            int n = sc.nextInt();		// 10 <= 원본 암호문의 길이 <= 20
            List<Integer> lst = new ArrayList();		// 원본 암호문
            for(int i=0; i<n; i++) {
                lst.add(sc.nextInt());
            }
            int cmd = sc.nextInt();		// 5 <= 명령어의 개수 <= 10
            
            // 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입
            for(int c = 0; c<cmd; c++) {
                sc.next();		// 삽입 명령문
                int x = sc.nextInt();
                int y = sc.nextInt();
                while(y-- > 0) {
                    lst.add(x++, sc.nextInt());
                }
            }
            
            // 처음 10개의 숫자를 출력
            System.out.print("#" + test_case + " ");
            for(int i=0; i<10; i++) {
            	System.out.print(lst.get(i) + " ");
            }
            System.out.println();
        }
    }
}