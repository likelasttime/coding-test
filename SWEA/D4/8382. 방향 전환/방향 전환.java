import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int tc = Integer.parseInt(br.readLine());        // 테스트 케이스 수
        
        for (int t=1; t<=tc; t++) {
            st = new StringTokenizer(br.readLine());
            int answer = 0;        // 최소 이동 횟수
            /*
             * -100 <= x1, y1, x2, y2 <= 100
             */
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            int a = Math.abs(x1 - x2);
            int b = Math.abs(y1 - y2);
            
            answer = Math.abs(a - (a + b) / 2) + Math.abs(b - (a + b) / 2);
            
            bw.write("#" + t + " " + (2 * ((a + b) / 2) + answer) + "\n");
        }
        
        bw.flush();
    }

}