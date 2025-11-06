import java.io.*;
import java.util.*;

public class Main {
	static int N;		// 1 <= 이진 트리의 노드의 개수 <= 26
	static Map<Character, Node> tree;
	
	static class Node {
		char left;
		char right;
		
		Node(char left, char right) {
			this.left = left;
			this.right = right;
		}
	}
	
	public static void preorderTraversal(char cur) {
		System.out.print(cur);
		if(tree.get(cur).left != '.') {
			preorderTraversal(tree.get(cur).left);
		}
		if(tree.get(cur).right != '.') {
			preorderTraversal(tree.get(cur).right);
		}
	}
	
	public static void inorderTraversal(char cur) {
		if(tree.get(cur).left != '.') {
			inorderTraversal(tree.get(cur).left);
		}
		System.out.print(cur);
		if(tree.get(cur).right != '.') {
			inorderTraversal(tree.get(cur).right);
		}
	}
	
	public static void postorderTraversal(char cur) {
		if(tree.get(cur).left != '.') {
			postorderTraversal(tree.get(cur).left);
		}
		if(tree.get(cur).right != '.') {
			postorderTraversal(tree.get(cur).right);
		}
		System.out.print(cur);
	}


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        tree = new HashMap();
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	char cur = st.nextToken().charAt(0); 	// 현재 노드
        	char left = st.nextToken().charAt(0);	// 왼쪽 자식 노드
        	char right = st.nextToken().charAt(0);	// 오른쪽 자식 노드
        	tree.put(cur, new Node(left, right));
        }
        
        preorderTraversal('A');
        System.out.println();
        inorderTraversal('A');
        System.out.println();
        postorderTraversal('A');
    }
}
