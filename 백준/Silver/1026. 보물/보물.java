import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;

public class Main {
    static int n;
    static PriorityQueue<Integer> a;
    static PriorityQueue<Integer> b;
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new PriorityQueue();
        b = new PriorityQueue(Collections.reverseOrder());
        for(int i=0; i<n; i++) {
            a.add(sc.nextInt());
        }
        for(int i=0; i<n; i++) {
            b.add(sc.nextInt());
        }
        for(int i=0; i<n; i++) {
            answer += a.poll() * b.poll();
        }
        System.out.println(answer);
    }
}