import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int n;
    static int budget;
    
    /*
        주어진 예산 내에서 처리할 수 있다면 예산 합계 반환
    */
    public static long check(int cur) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > cur) {  // 각 지방의 예산을 cur로 제한
                sum += cur;
            } else {
                sum += arr[i];
            }
        }
        return sum;  // 예산 합계 반환
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());        // 지방의 수
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxValue = Math.max(maxValue, arr[i]);  // 예산의 최대값을 찾기
        }
        budget = Integer.parseInt(br.readLine());    // 총 예산
        
        int left = 0;
        int right = maxValue;  // 예산의 최대값을 right로 설정
        int answer = 0;

        // 이진 탐색
        while (left <= right) {
            int mid = (left + right) / 2;
            long result = check(mid);

            if (result > budget) {  // 예산을 초과하면 더 작은 값으로 조정
                right = mid - 1;
            } else {  // 예산을 초과하지 않으면 더 큰 값으로 조정
                answer = mid;  // 예산을 초과하지 않으면 answer를 갱신
                left = mid + 1;
            }
        }

        // 결과 출력
        System.out.println(answer);  // 예산 내에서 가능한 최대값 출력
    }
}
