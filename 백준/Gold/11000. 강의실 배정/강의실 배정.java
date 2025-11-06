import java.io.*;
import java.util.*;

public class Main {
	static int N;	// 1 ≤ 수업의 수 ≤ 200,000
	
	static class Time {
		int s;		// 시작 시각
		int e;		// 끝나는 시각
		
		Time(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Time[] time = new Time[N];
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	int S = Integer.parseInt(st.nextToken());		// 0 <= 시작하는 시각 < 끝나는 시각 T
        	int T = Integer.parseInt(st.nextToken());		// 시작하는 시각 S < 끝나는 시각 <= 10^9
        	time[i] = new Time(S, T);
        }
        
        // 시작 시각순으로 정렬
        Arrays.sort(time, new Comparator<Time>() {
        	public int compare(Time t1, Time t2) {
        		return t1.s - t2.s;		// 빨리 시작하는 순으로 정렬
        	}
        });
        
        PriorityQueue<Time> pq = new PriorityQueue(new Comparator<Time>() {
        	public int compare(Time t1, Time t2) {
        		return t1.e - t2.e;		// 빨리 끝나는 순으로 정렬
        	}
        });
        int answer = 1;		// 강의실의 갯수
        pq.offer(time[0]);
        for(int i=1; i<N; i++) {
        	if(pq.peek().e <= time[i].s) {
        		pq.poll();
        		pq.offer(time[i]);
        	} else {
        		pq.offer(time[i]);
        		answer++;
        	}
        }
        System.out.println(answer);		// 강의실의 개수
    }
}
