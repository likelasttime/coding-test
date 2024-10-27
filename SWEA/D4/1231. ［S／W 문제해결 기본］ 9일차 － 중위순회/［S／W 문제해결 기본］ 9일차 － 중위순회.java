import java.io.*;
import java.util.StringTokenizer;

class Solution
{
    static class Tree {
        String ch;
        int left;
        int right;

        Tree(String ch, int left, int right) {
            this.ch = ch;
            this.left = left;
            this.right = right;
        }
    }

    public static void dfs(int cur) {
        if(cur != -1) {
            dfs(tree[cur].left);
            System.out.print(tree[cur].ch);
            dfs(tree[cur].right);
        }
    }

    static Tree[] tree;

    public static void main(String args[]) throws Exception
    {
        int T = 10;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = Integer.parseInt(br.readLine());		// 정점의 총 수
            tree = new Tree[n + 1];
            // 정점 정보 입력 받기
            while(n-- > 0) {
                st = new StringTokenizer(br.readLine());
                int cur = Integer.parseInt(st.nextToken());		// 현재 정점 번호
                String ch = st.nextToken();		// 현재 정점의 문자
                int left = -1;
                int right = -1;
               
                if(st.hasMoreTokens()) {
                    left = Integer.parseInt(st.nextToken());
                }

                if(st.hasMoreTokens()) {
                    right = Integer.parseInt(st.nextToken());
                }
                
                tree[cur] = new Tree(ch, left, right);
            }

            System.out.print("#" +  test_case + " ");
            dfs(1);
            System.out.println();
        }
    }
}