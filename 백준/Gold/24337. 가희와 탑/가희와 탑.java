import java.util.*;
/*
    다양한 높이의 건물들이 N개 존재
    가희는 건물들의 왼쪽에 위치
    단비는 건물들의 오른쪽에 위치
    가희의 오른쪽에 1번 건물이 있다.
    1 <= x <= N-1일때
    	x번 건물의 오른쪽에는 x+1번 건물이 있다.
    N번 건물의 오른쪽에는 단비가 있다.
    가희는 1번 건물을 볼 수 있다.
    k번 건물보다 왼쪽에 있는 건물들이 모두 k번 건물보다 높이가 낮다면
    	가희는 k번 건물을 볼 수 있다.
    단비는 N번 건물을 볼 수 있다.
    k번 건물보다 오른쪽에 있는 건물들이 모두 k번 건물보다 높이가 낮다면
    	단비는 k번 건물을 볼 수 있다.
*/
class Main {
    static int N;	// 1 <= 건물의 개수 <= 10^5
    static int A;	// 1 <= 가희가 볼 수 있는 건물의 개수 <= N
    static int B;	// 1 <= 단비가 볼 수 있는 건물의 개수 <= N
    static List<Integer> tower;
    
    public static void simulate() {
    	// 오른쪽을 바라보기
        for(int i=1; i<A; i++) {
        	tower.add(i);
        }
        
        tower.add(Math.max(A, B));
        
        // 왼쪽을 바라보기
        for(int i=B-1; i>=1; i--) {
        	tower.add(i);
        }
        
        if(A == 1) {
        	while(tower.size() < N) {
        		tower.add(1, 1);
        	}
        } else {
        	while(tower.size() < N) {
        		tower.add(0, 1);
        	}
        }
    }
    
    public static void main(String[] args) {    	
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	A = sc.nextInt();
    	B = sc.nextInt();
    	tower = new ArrayList();
    	
    	if(A + B > N + 1) {
    		System.out.print(-1);
    		return;
    	}
    	
    	simulate();
    	
    	for(int i=0; i<N; i++) {
    		System.out.print(tower.get(i) + " ");
    	}
    }
}