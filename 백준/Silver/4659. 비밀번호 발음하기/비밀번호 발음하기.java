import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String input = br.readLine();
            
            if(input.equals("end")) {
                break;
            }
            
            int vowelCnt = 0;       // 모음 갯수
            int vowelSequenceCnt = 0;       // 모음 연속 갯수
            int consonantSequenceCnt = 0;       // 자음 연속 갯수
            char prev = '.';     // 이전 글자
            boolean flag = true;
            
            for(int i=0; i<input.length(); i++) {
                char ch = input.charAt(i);
                if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {      // 모음이라면
                    vowelCnt++;
                    vowelSequenceCnt++;
                    consonantSequenceCnt = 0;       // 모음 연속 갯수 초기화
                } else {    // 자음이라면
                    consonantSequenceCnt++;
                    vowelSequenceCnt = 0;       // 자음 연속 갯수 초기화
                }
                
                if(vowelSequenceCnt >= 3 || consonantSequenceCnt >= 3) {    // 모음 또는 자음의 연속 갯수가 3개를 넘어가면
                    flag = false;
                    break;
                }
                
                if(prev == ch && ch != 'e' && ch != 'o') {      // 같은 글자가 연속된다면
                    flag = false;
                    break;
                }
                
                prev = ch;
            }
            
            if(vowelCnt == 0) {      // 모음이 하나도 없다면
                flag = false;
            }
            
            if(!flag) {
                System.out.println("<" + input + "> is not acceptable.");
            } else {
                System.out.println("<" + input + "> is acceptable.");
            }
        }
    }
}