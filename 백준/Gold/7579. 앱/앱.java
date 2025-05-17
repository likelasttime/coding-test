import java.util.*;
import java.io.*;

public class Main {
    final static int MAX_PRICE = 10000;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 1 <= 앱 갯수 <= 100
        int m = Integer.parseInt(st.nextToken());   // 1 <= 필요한 메모리 바이트 <= 10,000,000
        int[] memory = new int[n];
        int[] price = new int[n];
        int[] dp = new int[MAX_PRICE + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0; i<n; i++) {        // 앱의 갯수만큼 반복
            for(int j=MAX_PRICE; j>=price[i]; j--) {
                dp[j] = Math.max(dp[j], memory[i] + dp[j - price[i]]);
            }
        }
        for(int i=0; i<=MAX_PRICE; i++) {
            if(dp[i] >= m) {
                System.out.print(i);
                break;
            }
        }
    }
}
