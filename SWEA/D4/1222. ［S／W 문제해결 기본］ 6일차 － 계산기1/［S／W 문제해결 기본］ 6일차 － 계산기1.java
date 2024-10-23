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
			int len = sc.nextInt();			// 문자열 계산식의 길이
			String str = sc.next();		// 문자열 계산식
            Stack<Character> stack = new Stack();		// 연산자를 저장하는 스택
            Stack<Integer> stack2 = new Stack();		// 피연산자를 저장하는 스택
            StringBuilder sb = new StringBuilder();
            
            // 후위 표기법으로 변환
            for(int i=0; i<len; i++) {
            	char ch = str.charAt(i);
                if(!Character.isDigit(ch)) {		// 연산자라면
                    if(!stack.isEmpty()) {		// 우선순위가 같다면
                    	sb.append(stack.pop());
                    }
                    stack.push(ch);
                } else {		// 피연산자라면
                	sb.append(ch);
                }
            }
            
            if(!stack.isEmpty()) {		// 스택에 남아있는 연산자가 있으면
            	sb.append(stack.pop());
            }
            
            // 후위 표기법을 계산
            String back = sb.toString();
            for(int i=0; i<len; i++) {
                char ch = back.charAt(i);
            	if(Character.isDigit(ch)) {		// 피연산자라면
                	stack2.push(Integer.parseInt(String.valueOf(ch)));
                } else {		// 연산자라면
                    if(stack2.size() < 2) {
                    	break;
                    }
                	int num1 = stack2.pop();
                    int num2 = stack2.pop();
                    
                    stack2.push(num2 + num1);
                }
            }
            System.out.println("#" + test_case + " " + stack2.pop());
		}
	}
}