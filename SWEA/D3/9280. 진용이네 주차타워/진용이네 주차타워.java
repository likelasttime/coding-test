import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Solution
{
    static int n;		// 주차 공간 갯수
    static int m;		// 차량 대수
    static int[] rate;	// 단위 무게당 요금 배열
    static int[] weight;		// 차량의 무게
    static int[] arr;		// 주차장
    static int answer;
    
    public static void findParkingPlace(int car) {
    	// 주차할 자리를 찾는다.
        for(int i=0; i<n; i++) {
        	if(arr[i] == 0) {
            	arr[i] = car;
                answer += weight[car - 1] * rate[i];
                break;
            }
        }
    }
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			n = sc.nextInt();		// 주차공간 갯수
            m = sc.nextInt();		// 차량의 대수
            answer = 0;		// 오늘 하루 벌어들인 수입
            rate = new int[n];
            weight = new int[m];
            arr = new int[n];
            
            Queue<Integer> que = new LinkedList();		// 들어온 차 순서
            Queue<Integer> waitQue = new LinkedList();	// 대기열 큐
            
            // 주차공간의 단위 무게당 요금 입력받기
            for(int i=0; i<n; i++) {
                rate[i] = sc.nextInt();
            }
            
            // 차량의 무게 입력받기
            for(int i=0; i<m; i++) {
            	weight[i] = sc.nextInt();
            }
            
            // 차량들의 주차장 출입 순서 입력받기
            for(int i=0; i<2*m; i++) {
                que.add(sc.nextInt());
            }
            
            int cnt = 0;		// 현재 주차한 차 대수
            while(!que.isEmpty()) {
            	int car;
                
                // 주차할 수 있고, 대기 큐에 차가 대기하고 있다면
                if(cnt < n && !waitQue.isEmpty()) {
                	car = waitQue.poll();
                    
                    // 주차할 공간 찾기
                    findParkingPlace(car);
                    cnt++;
                } else {
                    car = que.poll();
                	if(car > 0) {	// 들어온 차라면
                    	if(cnt < n) {		// 주차할 자리가 있다면
                            findParkingPlace(car);
                            cnt++;
                        } else {		// 주차할 자리가 없다면
                        	waitQue.add(car);		// 대기 큐에 추가
                        }
                    } else {	// 나갈 차라면
                    	cnt--;
                        // 주차 자리를 비우기
                        for(int i=0; i<n; i++) {
                        	if(arr[i] == car * -1) {
                            	arr[i] = 0;
                                break;
                            }
                        }
                    }
                }         
            }
            
            // 벌어들인 수입 출력
            System.out.println("#" + test_case + " " + answer);
		}
	}
}