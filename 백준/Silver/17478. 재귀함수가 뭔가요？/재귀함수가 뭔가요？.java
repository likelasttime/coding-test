import java.util.Scanner;

class Main {

    static String[] s = {"어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.", 
            "\"재귀함수가 뭔가요?\"", 
            "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.", 
            "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.", 
            "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"",
            "\"재귀함수는 자기 자신을 호출하는 함수라네\"",
            "라고 답변하였지."
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();        // 재귀 횟수

        System.out.println(s[0]);
        dfs(0, n, 0);
        System.out.println(s[6]);
        System.out.println();
    }

    public static void dfs(int depth, int n, int cnt) {
        if(depth == n) {
        	for(int j=0; j<4*depth; j++) {        // _ 추가
                System.out.print("_");
            }
            System.out.println(s[1]);
            for(int j=0; j<4*depth; j++) {        // _ 추가
                System.out.print("_");
            }
            System.out.println(s[5]);
            return;
        }
        if(depth < n) {
            // n번 반복해야하는데
            for(int i=1; i<5; i++) {
                for(int j=0; j<4*depth; j++) {        // _ 추가
                    System.out.print("_");
                }
                System.out.println(s[i]);
            }
        }
        dfs(depth+1, n, cnt++);
        for(int j=0; j<4*(depth+1); j++) {        // _ 추가
            System.out.print("_");
        }
        System.out.println("라고 답변하였지.");

    }

}