import java.util.Scanner;

public class Main {
    static class Body {
        int x;  // 몸무게
        int y;  // 키

        Body(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 전체 사람의 수
        Body[] body = new Body[n];
        int[] answer = new int[n];
        // 각 사람의 몸무게와 키를 입력받기
        for(int i=0; i<n; i++) {
            body[i] = new Body(sc.nextInt(), sc.nextInt());
        }

        for(int i=0; i<n; i++) {
            int rank = 1;
            for(int j=0; j<n; j++) {
                if(i == j) {
                    continue;
                }
                if(body[i].x < body[j].x && body[i].y < body[j].y) {    // 나보다 키, 몸무게 모두 더 큰 사람이 있으면
                    rank++;
                }
            }
            System.out.println(rank);
        }

    }
}