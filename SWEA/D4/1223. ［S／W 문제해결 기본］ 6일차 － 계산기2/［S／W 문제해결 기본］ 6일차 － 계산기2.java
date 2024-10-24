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
			int len = sc.nextInt();		// 테스트케이스의 길이
            String str = sc.next();
            Stack<Character> stack1 = new Stack();
            Stack<Integer> stack2 = new Stack();
            StringBuilder sb = new StringBuilder();
            
            // 후위 표기식 생성
            for(int i=0; i<len; i++) {
            	char ch = str.charAt(i);
                if(!Character.isDigit(ch)) {		// 연산자라면
                    while(!stack1.isEmpty()) {		// 스택이 비어있지 않고
                        char top = stack1.peek();
                        if(top == ch) {		// 스택의 가장 상단에 있는 연산자와 같으면
                        	sb.append(stack1.pop());
                            //stack1.push(ch);
                            break;
                        } else if(top == '*') {		// 현재 연산자를 넣기 위해 우선순위가 더 높은 연산자를 뽑기
                        	sb.append(stack1.pop());
                            //stack1.push(ch);
                        } else {		// 현재 연산자가 *이고 스택의 상단이 +라서 우선순위가 더 높을 때
                            break;
                        }
                    }
                	stack1.push(ch);
                } else {
                	sb.append(ch);
                }
            }
            
            // 스택에 남은 연산자 모두 추가
            while(!stack1.isEmpty()) {
            	sb.append(stack1.pop());
            }   
            
            // 후위 표기식 계산
            String reverse = sb.toString();
            for(int i=0; i<len; i++) {
            	char ch = reverse.charAt(i);
                if(Character.isDigit(ch)) {		// 피연산자라면
                    stack2.push(Integer.parseInt(String.valueOf(ch)));
                } else {		// 연산자라면
                	if(stack2.size() >= 2) {
                    	int a = stack2.pop();
                        int b = stack2.pop();
                        if(ch == '*') {
                       		stack2.push(b * a);
                        } else {
                            stack2.push(b + a);
                        }
                    }
                }
            }
            System.out.println("#" + test_case + " " + stack2.pop());
		}
	}
}