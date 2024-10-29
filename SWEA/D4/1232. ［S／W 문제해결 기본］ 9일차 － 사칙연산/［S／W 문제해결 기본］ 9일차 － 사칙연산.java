import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Stack;

class Solution
{
    static class Tree {
        int num;		// 정점 번호
        String value;		// 양의 정수
        String op;		// 연산자
        int left;		// 왼쪽 자식
        int right;		// 오른쪽 자식

        Tree(int num, String value, String op, int left, int right) {
            this.num = num;
            this.value = value;
            this.op = op;
            this.left = left;
            this.right = right;
        }
    }

    final static List<String> opLst = Arrays.asList("+", "-", "*", "/");

    static Tree[] tree = new Tree[1001];
    static Stack<Integer> stack;
    static StringBuilder sb;

    public static void dfs(int cur) {
        if(cur != -1) {
            dfs(tree[cur].left);
            dfs(tree[cur].right);
            if(!tree[cur].op.isEmpty()) {
                sb.append(tree[cur].op);	// 연산자를 스택에 추가
            } else {
                sb.append(tree[cur].value + " ");
            }
        }
    }

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            stack = new Stack();
            sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine());	// 정점의 개수
            // 정점의 정보 입력 받기
            for(int i=1; i<=n; i++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                String value = st.nextToken();		// 연산자 혹은 양의 정수
                int left = -1;
                int right = -1;

                if(opLst.contains(value)) {		// 연산자라면
                    left = Integer.parseInt(st.nextToken());
                    right = Integer.parseInt(st.nextToken());
                    tree[i] = new Tree(num, "-1", value, left, right);
                } else {		// 피연산자라면
                    tree[i] = new Tree(num, value, "", left, right);
                }
            }

            dfs(1);

            // 후위 표기법 계산
            String reverse = sb.toString();
            String numStr = "";
            for(int i=0; i<reverse.length(); i++) {
                if(opLst.contains(String.valueOf(reverse.charAt(i)))) {		// 연산자라면
                    int a = stack.pop();
                    int b = stack.pop();

                    if(reverse.charAt(i) == '+') {
                        stack.push(b + a);
                    } else if(reverse.charAt(i) == '-') {
                        stack.push(b - a);
                    } else if(reverse.charAt(i) == '/') {
                        stack.push(b / a);
                    } else {
                        stack.push(b * a);
                    }
                } else if(reverse.charAt(i) == ' ') {
                    stack.push(Integer.parseInt(numStr));
                    numStr = "";
                } else {		// 피연산자라면
                    if(reverse.charAt(i) == ' ') {
                        continue;
                    }
                    numStr += reverse.charAt(i);
                }
            }

            System.out.println("#" + test_case + " " + stack.pop());
        }
    }
}