import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); // 출력 최적화
        
        int N = Integer.parseInt(st.nextToken()); // 칭호 갯수
        int M = Integer.parseInt(st.nextToken()); // 전투력 갯수
        
        String[] title = new String[N]; // 칭호
        int[] titlePower = new int[N]; // 칭호 기준 전투력
        
        // 칭호와 전투력 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            title[i] = st.nextToken();
            titlePower[i] = Integer.parseInt(st.nextToken());
        }
        
        // 전투력 기준으로 칭호와 전투력 배열을 정렬
        // titlePower를 기준으로 title도 함께 정렬
        Integer[] indices = new Integer[N];
        for (int i = 0; i < N; i++) {
            indices[i] = i;
        }
        
        Arrays.sort(indices, (a, b) -> Integer.compare(titlePower[a], titlePower[b]));
        
        // 전투력에 대해 이진 탐색을 사용하여 적합한 칭호를 찾아 출력
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            
            int start = 0;
            int last = N - 1;
            
            while (start <= last) {
                int mid = (start + last) / 2;
                
                if (titlePower[indices[mid]] < num) {
                    start = mid + 1;
                } else {
                    last = mid - 1;
                }
            }
            
            sb.append(title[indices[start]]).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}