import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int row = h / (n + 1);
        int col = w / (m + 1);
        if(h % (n + 1) != 0) {
            row++;
        }
        if(w % (m + 1) != 0) {
            col++;
        }
        System.out.print(row * col);
    }
}