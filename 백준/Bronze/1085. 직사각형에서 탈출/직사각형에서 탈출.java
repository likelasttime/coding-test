import java.util.*;

public class Main {
    public static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();
        int answer = Integer.MAX_VALUE;

        // 가로 탐색
        for(int i=0; i<=h; i++) {
            // 윗변
            answer = Math.min(answer, getDistance(x, y, w, i));
            // 아랫변
            answer = Math.min(answer, getDistance(x, y, 0, i));
        }

        // 세로 탐색
        for(int i=0; i<=w; i++) {
            // 왼쪽 변
            answer = Math.min(answer, getDistance(x, y, i, 0));
            answer = Math.min(answer, getDistance(x, y, i, h));
        }

        System.out.print(answer);
    }
}