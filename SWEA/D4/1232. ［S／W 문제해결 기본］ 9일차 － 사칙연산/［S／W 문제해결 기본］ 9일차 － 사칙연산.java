import java.util.*;
import java.io.*;

class Solution {
    static int N;
    static StringBuilder sb;
    static List<Node> nodes = new ArrayList<>();
    static Stack<Double> stack;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            nodes = new ArrayList<>();
            stack = new Stack<>();

            for (int i = 0; i <=N; i++) {
                Node n = new Node();
                nodes.add(n);
            }

            for (int i = 1; i <=N; i++){
                String[] str = br.readLine().split(" ");
                Node node = nodes.get(Integer.parseInt(str[0]));
                if (str.length==4){
                    node.value = str[1];
                    node.left = nodes.get(Integer.parseInt(str[2]));
                    node.right = nodes.get(Integer.parseInt(str[3]));
                }else {
                    node.value = str[1];
                }
            }
            postOrder(nodes.get(1));
            int result = stack.pop().intValue();
            sb.append("#").append(test_case).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());

    }

    public static void postOrder(Node node){
        if (node.left==null||node.right==null){
            getResult(node.value);
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        getResult(node.value);
    }

    public static void getResult(String v){
        if (!(v.equals("+")||v.equals("-")||v.equals("*") ||v.equals("/"))){
            stack.push(Double.parseDouble(v));
            return;
        }
        Double b = stack.pop();
        Double a = stack.pop();
        if (v.equals("+")) stack.push(a + b);
        else if (v.equals("-")) stack.push(a - b);
        else if (v.equals("/")) stack.push(a / b);
        else stack.push(a * b);

    }

    static class Node{
        private String value;
        private Node left;
        private Node right;
    }
}