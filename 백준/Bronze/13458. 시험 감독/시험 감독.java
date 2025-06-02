import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());        // 1 <= 시험장의 개수 <= 1,000,000
        long answer = 0;
        // 각 시험장에 있는 응시자의 수 입력받기
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());       // 총감독관이 한 시험장에서 감시할 수 있는 응시자의 수
        int c = Integer.parseInt(st.nextToken());       // 부감독관이 한 시험장에서 감시할 수 있는 응시자의 수

        for(int i=0; i<n; i++) {
            // 총감독관을 넣고 나머지는 부감독관으로 채우는 경우
            int insertB = Math.max(0, arr[i] - b);
            int case1 = insertB % c > 0 ? insertB / c + 1 : insertB / c;
            answer += (case1 + 1);
        }

        // 필요한 감독관 수의 최솟값 출력
        System.out.print(answer);
    }
}