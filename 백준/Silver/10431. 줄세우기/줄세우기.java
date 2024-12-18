import java.io.*;
import java.util.StringTokenizer;

public class Main {
    final static int CHILD_CNT = 20;

    public static int sortChild(int[] arr) {
        int answer = 0;
        for(int i=0; i<CHILD_CNT; i++) {
            for(int j=0; j<i; j++) {    // 내 앞에 있는 사람들
                if(arr[i] < arr[j]) {   // 내 앞에 있는 사람이 나보다 키가 더 크면
                    answer++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int p = Integer.parseInt(br.readLine());        // 1 <= 테스트 케이스의 수 <= 1000
        for(int tc=1; tc<=p; tc++) {
            int[] arr = new int[CHILD_CNT];
            st = new StringTokenizer(br.readLine());
            st.nextToken();     // 테스트 케이스 번호
            int idx = 0;
            // 20명 아이들의 키를 입력받기
            while(st.hasMoreTokens()) {
                arr[idx++] = Integer.parseInt(st.nextToken());
            }
            bw.write(tc + " " + sortChild(arr) + "\n");
        }
        bw.flush();
    }
}