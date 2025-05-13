import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] memory = new int[n];
        int[] price = new int[n];
        for (int i = 0; i < n; i++) {
            memory[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            price[i] = sc.nextInt();
        }

        int maxCost = 100 * n;
        int[] dp = new int[maxCost + 1]; // dp[비용] = 확보한 메모리

        for (int i = 0; i < n; i++) {
            for (int j = maxCost; j >= price[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - price[i]] + memory[i]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= m) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
