import java.util.Scanner;
import java.util.Arrays;

class Main {
    static class Node {
        int lastTime;       // 마지막 제출 시간
        int sum;            // 최종 점수
        int totalCnt;       // 풀이 횟수
        int[] problem;      // 각 문제별 점수

        Node(int lastTime, int sum, int totalCnt, int[] problem) {
            this.lastTime = lastTime;
            this.sum = sum;
            this.totalCnt = totalCnt;
            this.problem = problem;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();       // 테스트 케이스 개수
        for(int tc=1; tc<=t; tc++) {
            int n = sc.nextInt();       // 3 <= 팀의 개수 <= 100
            int k = sc.nextInt();       // 3 <= 문제의 개수 <= 100
            int id = sc.nextInt();      // 내 팀 ID
            int m = sc.nextInt();       // 3 <= 로그 엔트리의 개수 <= 10,000
            int answer = 1;
            Node[] node = new Node[n + 1];
            for(int i=0; i<=n; i++) {
                node[i] = new Node(0, 0, 0, new int[k + 1]);
            }
            for(int i=0; i<m; i++) {
                int tId = sc.nextInt();     // 1 <= 각 팀 ID <= n
                int j = sc.nextInt();       // 문제 번호
                int s = sc.nextInt();       // 0 <= 획득한 점수 <= 100
                int prevScore = node[tId].problem[j];
                if(prevScore < s) {
                    node[tId].sum -= prevScore;
                    node[tId].sum += s;
                    node[tId].problem[j] = s;
                }
                node[tId].totalCnt++;       // 풀이 횟수 증가
                node[tId].lastTime = i;     // 마지막 제출 시간 갱신
            }
            // 순위 세기
            for(int i=1; i<=n; i++) {
                if(id == i) {       // 나 자신은 건너뛰기
                    continue;
                }
                if(node[id].sum < node[i].sum) {      // 최종 점수 비교
                    answer++;
                } else if(node[id].sum == node[i].sum && node[id].totalCnt > node[i].totalCnt) {       // 풀이를 제출한 횟수 비교
                    answer++;
                } else if(node[id].sum == node[i].sum && node[id].totalCnt == node[i].totalCnt && node[id].lastTime > node[i].lastTime) {       // 마지막 제출 시간 비교
                    answer++;
                }
            }

            System.out.println(answer);
        }
    }
}