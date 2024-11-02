import java.util.Scanner;
import java.util.Stack;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int len = sc.nextInt(); 	// 테스트케이스의 길이
            String str = sc.next();
            int answer = 1;		// 유효성 여부
			Stack<Character> stack = new Stack();
            
            for(int i=0; i<len; i++) {
            	char ch = str.charAt(i);
                if(ch == '(' || ch == '[' || ch == '{' || ch == '<') {		// 열린 괄호는 스택에 추가
                	stack.add(ch);
                } else {		// 닫힌 괄호라면
                	if(stack.isEmpty()) {
                        answer = 0;
                    	break;
                    } else if((stack.peek() == '(' && ch == ')') || (stack.peek() == '[' && ch == ']') || (stack.peek() == '{' && ch == '}') || (stack.peek() == '<' && ch == '>')) {
                        stack.pop();
                    } else {		// 괄호가 안 맞으면
                    	answer = 0;
                        break;
                    }
                }
            }
            
            if(!stack.isEmpty()) {
            	answer = 0;
            }
            
            System.out.println("#" + test_case + " " + answer);
		}
	}
}