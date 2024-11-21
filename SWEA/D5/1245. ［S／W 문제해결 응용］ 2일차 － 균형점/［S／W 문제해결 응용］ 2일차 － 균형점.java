import java.util.Scanner;
import java.io.IOException;

class Solution {
    static int n;
    static int[] xArr;
    static int[] weights;

    public static double binarySearch(int idx, int depth, double cur, double left, double right) {
        if(depth == 100) {      // 재귀호출 횟수를 제한
            return cur;
        }

        double f = 0.0;
        double value = 0.0;

        // 왼쪽 계산
        for(int i=0; i<=idx; i++) {
            f += weights[i] / Math.pow(cur - xArr[i], 2.0);
        }
        // 오른쪽 계산
        for(int i=idx+1; i<n; i++) {
            f -= weights[i] / Math.pow(cur - xArr[i], 2.0);
        }

        if(f < 0) {     // 오른쪽이 더 크면
            value = binarySearch(idx, depth + 1, (cur + left) / 2.0, left, cur);
        } else if(f > 0) {      // 왼쪽이 더 크면
            value = binarySearch(idx, depth + 1, (cur + right) / 2.0, cur, right);
        } else {
            value = cur;
        }
        return value;
    }

    /*
        인력 구하기
     */
    public static double calF(int start, int end, double center) {
        double f = 1;
        //double distance = 1;
        for(int i=start; i<end; i++) {
            if(i >= n) {
                break;
            }
            f += weights[i] / Math.pow(xArr[i] - center, 2.0);
            //distance *= Math.abs(xArr[i] - center);
        }
        return f;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        for(int t=1; t<=tc; t++) {
            n = sc.nextInt();    // 2 <= 자성체의 개수 <= 10
            xArr = new int[n];
            weights = new int[n];
            // x좌표 입력 받기
            for(int i=0; i<n; i++) {
                xArr[i] = sc.nextInt();
            }
            // 질량 값 입력받기
            for(int i=0; i<n; i++) {
                weights[i] = sc.nextInt();
            }

            System.out.print("#" + t + " ");
            for(int i=0; i<n-1; i++) {
                System.out.printf("%.10f ", binarySearch(i, 0, (xArr[i] + xArr[i + 1]) / 2.0, xArr[i], xArr[i + 1]));
            }
            System.out.println();
        }
    }
}