import java.util.Scanner;

public class Solution {
    static int n;
    static int[][] arr;
    static int[][] answer1;
    static int[][] answer2;
    static int[][] answer3;

    public static void print() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(answer1[i][j]);
            }
            System.out.print(" ");
            for(int j=0; j<n; j++) {
                System.out.print(answer2[i][j]);
            }
            System.out.print(" ");
            for(int j=0; j<n; j++) {
                System.out.print(answer3[i][j]);
            }
            System.out.println();
        }
    }

    public static void rotate90() {
        for(int i=0; i<n; i++) {
            for(int j=n-1; j>=0; j--) {
                answer1[i][n - j - 1] = arr[j][i];
            }
        }
    }

    public static void rotate180() {
        for(int i=n-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                answer2[n - i - 1][n - j - 1] = arr[i][j];
            }
        }
    }

    public static void rotate270() {
        for(int i=n-1; i>=0; i--) {
            for(int j=0; j<n; j++) {
                answer3[n - i - 1][j] = arr[j][i];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();        // 전체 테스트 케이스의 수

        for(int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();       // 행
            arr = new int[n][n];
            answer1 = new int[n][n];
            answer2 = new int[n][n];
            answer3 = new int[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            rotate90();
            rotate180();
            rotate270();

            System.out.println("#" + tc);
            print();
        }
    }
}