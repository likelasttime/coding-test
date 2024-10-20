import java.util.Scanner;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        //T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int test_num = sc.nextInt();		// 테스트 케이스 번호
            String keyword = sc.next();		// 찾을 문자열
            String sentence = sc.next();	// 검색할 문장

            int answer = 0;		// 빈도 수
            int len = keyword.length();		// 찾을 문자열 길이
            int left = 0;		// 검색할 문장 인덱스
            int cnt = 0;		// 찾을 문자열 인덱스
            while(left <= sentence.length() - len) {		
                cnt = 0;
                while(cnt < len && sentence.charAt(left + cnt) == keyword.charAt(cnt)) {		// 문자가 같으면
                    cnt++;
                }

                if(cnt == len) {		// 문자열을 찾았다면
                    left += cnt;		// 시작점을 길이만큼 건너뛰기
                    answer++;
                } else {		// 문자열을 못 찾았다면
                    left++;
                }
            }
            
            System.out.println("#" + test_case + " " + answer);
        }
    }
}