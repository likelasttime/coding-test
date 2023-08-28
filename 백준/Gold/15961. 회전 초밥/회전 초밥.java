import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_15961_회전초밥
 * 
 * [아이디어]
 * 슬라이딩 윈도우
 * 원형인 것을 고려해야 한다. -> 나머지 연산자 사용
 *
 * </pre>
 */

public class Main {
    
    static int answer;
    static int cnt;
    static int c;
    static int k;
    static int n;		// 접시 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        answer = 0;            // 초밥 가짓수의 최댓값
        
        n = Integer.parseInt(st.nextToken());        // 접시 수
        int d = Integer.parseInt(st.nextToken());        // 초밥의 가짓수
        k = Integer.parseInt(st.nextToken());        // 연속해서 먹는 접시 수
        c = Integer.parseInt(st.nextToken());        // 쿠폰 번호
        
        /*
         * 벨트의 한 위치부터 시작해서 회전 방향을 따라갈 때 초밥의 종류
         */
        int[] arr = new int[n+1];
        for(int i=1; i<=n; i++) {
            int val = Integer.parseInt(br.readLine());
            arr[i] = val;            // 초밥 종류
        }
        
        /*
         * k개를 골랐을 때 초밥의 종류 개수 계산
         */
        int[] cntArr = new int[1+d];
        cnt = 0;        // k개를 골랐을 때 초밥의 종류 개수
        for(int i=1; i<=k; i++) {
            if(++cntArr[arr[i]] == 1) {
                cnt++;
                if(getFree(cntArr)) {
                	answer = Math.max(answer, cnt+1);
                }else {
                	answer = Math.max(answer, cnt);
                }
            }
        }
        
        /*
         * 슬라이딩 윈도우
         */
        for(int i=k+1; i<n+k; i++) {
            slidingWindow(i, arr, cntArr);
            if(answer == k+1) {
            	break;
            }
        }
        
        System.out.println(answer);

    }
    
    public static void slidingWindow(int i, int[] arr, int[] cntArr) {
        if(++cntArr[arr[(i-1) % n + 1]] == 1) {        // 새로운 초밥 발견
            cnt++;
        }
        
        if(--cntArr[arr[(i - k - 1) % n +1]] == 0) {        // 가장 왼쪽 값을 하나 없애고, 갯수가 0이면 초밥 종류가 줄어든다
            cnt--;
        }
        
        if(getFree(cntArr)) {		// 무료 초밥을 받을 수 있는지
        	answer = Math.max(answer, cnt+1);
        }else {
        	answer = Math.max(answer, cnt);
        }
    }
    
    public static boolean getFree(int[] cntArr) {
    	if(cntArr[c] == 0) {		// 쿠폰으로 주는 초밥이 현재 범위 내에 없으면
            return true;
        }
    	return false;
    }

}