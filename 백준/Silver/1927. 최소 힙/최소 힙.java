import java.util.Scanner;
import java.util.PriorityQueue;

class Main {
    static int n;       // 1 <= 연산의 개수 <= 100,000
    static PriorityQueue<Integer> pq;

    public static int getMinValue() {
        if(pq.isEmpty()) {
            return 0;
        }
        return pq.poll();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        pq = new PriorityQueue();
        for(int i=0; i<n; i++) {
            int x = sc.nextInt();
            if(x > 0) {
                pq.add(x);
            } else{
                System.out.println(getMinValue());
            }
        }
    }
}