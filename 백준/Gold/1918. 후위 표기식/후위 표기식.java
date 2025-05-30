import java.util.*;
import java.io.*;

public class Main {
    static String expression;  // 수식(+, -, *, /, '(', ')'로 이루어짐), 길이는 100을 넘지 않음

    public static void transform() {
        StringBuilder sb = new StringBuilder();
        int n = expression.length();
        Stack<Character> stack = new Stack();
        for(int i=0; i<n; i++) {
            char ch = expression.charAt(i);
            if(Character.isAlphabetic(ch)) {
                sb.append(ch);
            } else if(ch == '(') {
              stack.add(ch);
            } else if(ch == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                if(!stack.isEmpty()) stack.pop();    // 여는 괄호는 출력하지 않는다
            } else {
                while(!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(ch)) {
                    sb.append(stack.pop());
                }
                stack.add(ch);
            }
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }

    public static int getPriority(char ch) {
        if(ch == '(') {
            return 0;
        } else if(ch == '+' || ch == '-') {
            return 1;
        }
        return 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        expression = br.readLine();
        transform();
    }
}
