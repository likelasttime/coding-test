import java.util.Scanner;
import java.util.Stack;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int len = sc.nextInt();  // 테스트 케이스 길이
            String str = sc.next();   // 테스트 케이스
            Stack<Character> stack = new Stack<>();
            StringBuilder sb = new StringBuilder();

            // 후위 표기식 생성
            for (int i = 0; i < len; i++) {
                char ch = str.charAt(i);
                if (Character.isDigit(ch)) {  // 피연산자라면
                    sb.append(ch);
                } else if (ch == '(') {  // 여는 괄호는 스택에 넣기
                    stack.push(ch);
                } else if (ch == ')') {  // 닫는 괄호를 만나면
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();  // 여는 괄호 제거
                } else {  // 연산자라면
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                        sb.append(stack.pop());
                    }
                    stack.push(ch);
                }
            }

            // 남아있는 연산자 처리
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }

            // 후위 연산자 계산
            String reverse = sb.toString();
            Stack<Integer> stack2 = new Stack<>();
            for (int i = 0; i < reverse.length(); i++) {
                char ch = reverse.charAt(i);
                if (Character.isDigit(ch)) {  // 피연산자라면
                    stack2.push(Character.getNumericValue(ch));
                } else {  // 연산자라면
                    if (stack2.size() >= 2) {
                        int a = stack2.pop();
                        int b = stack2.pop();
                        if (ch == '*') {
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

    private static int precedence(char operator) {
        switch (operator) {
            case '*':
                return 2;
            case '+':
                return 1;
            default:
                return 0;
        }
    }
}