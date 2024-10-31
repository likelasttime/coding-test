import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Collections;

class Solution
{
    final static int SIZE = 100;
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = 10;
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int n = sc.nextInt(); // 덤프 횟수
            PriorityQueue<Integer> minHeap = new PriorityQueue();
            PriorityQueue<Integer> maxHeap = new PriorityQueue(Collections.reverseOrder());
            // 각 상자의 높이 값 입력받기
            for(int i=0; i<SIZE; i++) {
                int height = sc.nextInt();
            	minHeap.add(height);
                maxHeap.add(height);
            }
            while(n-- > 0) {
            	int minValue = minHeap.poll();
                int maxValue = maxHeap.poll();
                minHeap.add(minValue + 1);
                maxHeap.add(maxValue - 1);
            }
            int answer = maxHeap.poll() - minHeap.poll();
            System.out.println("#" + test_case + " " + answer);
		}
	}
}