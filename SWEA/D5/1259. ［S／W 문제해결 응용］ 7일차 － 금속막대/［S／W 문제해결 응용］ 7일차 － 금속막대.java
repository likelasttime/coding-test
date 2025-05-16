import java.util.*;

public class Solution {
    static int[][] arr;
    static boolean[] visit;
    static int n;       // 원형 금속 막대의 개수
    static Nail[] nails;
    static int answer;
    static List<Integer> answerLst;
    static List<Integer> lst;

    static class Nail {
        int left;
        int right;
        Nail(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void dfs(int cnt) {
        if(cnt >= 2) {
            int a = lst.get(cnt - 2);
            int b = lst.get(cnt - 1);
            if(nails[a].right != nails[b].left) {
                return;
            }
        }
        if(answer < cnt) {
            answer = cnt;
            answerLst = new ArrayList<>();
            for(int i : lst) {
                answerLst.add(i);
            }
        }
        for(int i=0; i<n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                lst.add(i);
                dfs(cnt + 1);
                visit[i] = false;
                lst.remove(lst.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();   // 테스트 케이스 갯수
        for(int tc=1; tc<=t; tc++) {
            n = sc.nextInt();
            answer = 0;
            nails = new Nail[n];
            lst = new ArrayList();
            answerLst = new ArrayList();
            visit = new boolean[n];
            for(int i=0; i<n; i++) {
                nails[i] = new Nail(sc.nextInt(), sc.nextInt());
            }
            dfs(0);
            System.out.print("#" + tc + " ");
            for(int i : answerLst) {
                System.out.print(nails[i].left + " " + nails[i].right + " ");
            }
            System.out.println();
        }
    }
}