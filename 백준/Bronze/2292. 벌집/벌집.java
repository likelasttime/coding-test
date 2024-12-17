import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 1;     // 거리
        int end = 1;
        while(true) {
            if(n <= end) {
                break;
            }
            end += 6 * answer;
            answer++;       // 거리 증가
        }
        System.out.print(answer);
    }
}