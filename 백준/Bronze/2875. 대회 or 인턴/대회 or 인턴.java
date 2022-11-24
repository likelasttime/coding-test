// 여학생 2명, 남학생 1명씩 묶어서 만들 수 있는 팀의 최대 개수를 구하는 문제
// 인턴쉽에 참여해야 하는 인원을 제외하고 팀을 만든다
// 여학생과 남학생 수 중에서 더 작은 쪽을 팀의 최대 개수로 한다
// 인턴쉽 인원이 부족하면 그룹을 줄인다
import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();       // 여학생 수(0 <= N <= 100)
        int M = sc.nextInt();       // 남학생 수(0 <= M <= 100)
        int K = sc.nextInt();       // 인턴쉽에 참여해야 하는 인원(0 <= K <= M+N)
        int answer = 0;

        int max_n = N / 2;      // 여학생으로 만들 수 있는 최대 팀 수
        if(max_n > M){
            answer = M;
        }else{
            answer = max_n;
        }

        K -= (N + M) - (3 * answer);    // 그룹을 만들고 남은 인원으로 인턴쉽에 참가한다

        if(K > 0){  // 인턴십에 참여해야 하는 인원을 채우기 위해 만든 그룹을 줄인다
            K -= 1;         // K가 3이면 -2를 하게 되므로 1을 줄였다
            answer -= K / 3 + 1;        // 3 미만이여도 그룹을 줄여야해서 +1을 한다
        }

        System.out.println(answer);     // 최대의 팀 수
    }
}