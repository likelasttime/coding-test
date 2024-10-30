import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.io.*;
import java.util.StringTokenizer;

class Solution
{
    final static List<String> lst = Arrays.asList("+", "-", "*", "/");

    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = 10;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = Integer.parseInt(br.readLine());	// 정점의 총 수
            int answer = 1;
            // 정점 정보 입력 받기
            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());		// 정점 번호
                String value = st.nextToken();		// 연산자 또는 숫자
                if(!lst.contains(value)) {	// 피연산자라면
                    if(st.hasMoreTokens()) {
                        answer = 0;
                    }
                }
                while(st.hasMoreTokens()) {
                    st.nextToken();
                }
            }
            System.out.println("#" + test_case + " " + answer);
        }
    }
}