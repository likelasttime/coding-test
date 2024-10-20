import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
	
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int test_num = sc.nextInt();	// 테이트케이스 번호
            Integer[] scores = new Integer[1000];
            
            // 학생들의 점수 입력받기
            for(int i=0; i<1000; i++) {
            	scores[i] = sc.nextInt();
            }
            
            // 내림차순 정렬
			Arrays.sort(scores, Collections.reverseOrder());
            
            int pre = scores[0];		// 이전 학생의 점수
            int answer = 1;				// 최빈수
            int maxCnt = 1;				// 최대 빈도 수
            int cnt = 1;					// 현재 빈도 수
            for(int i=1; i<1000; i++) {
            	if(scores[i] == pre) {		// 같은 수가 나왔으면
                	cnt++;
                } else {
                    pre = scores[i];		// 이전 수 갱신
                    if(maxCnt < cnt) {		// 현재 수가 빈도 수가 더 높으면
                    	maxCnt = cnt;			// 최대 빈도 수 갱신
                        answer = scores[i];			// 최빈수 갱신
                    }
                    cnt = 1;		// 현재 빈도 수 초기화
            	}
			}
            answer++;
            System.out.println("#" + test_num + " " + answer);
        }   
	}
}