import java.io.*;
import java.util.*;

public class Main {
    static long[] count = new long[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        solve(n);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append(count[i]).append(" ");
        System.out.println(sb);
    }

    static void solve(long n) {
        long start = 1;
        long digit = 1;  // 현재 자리수 (1, 10, 100, ...)
        while (start <= n) {
            // 뒤 자리를 0으로 맞춤
            while (n % 10 != 9 && start <= n) {
                addCount(n, digit);
                n--;
            }
            if (n < start) break;

            // 앞 자리를 0으로 맞춤
            while (start % 10 != 0 && start <= n) {
                addCount(start, digit);
                start++;
            }
            if (start > n) break;

            // 이제 start는 0으로 끝나고 n은 9로 끝남
            long cnt = (n / 10 - start / 10 + 1);
            for (int i = 0; i < 10; i++) {
                count[i] += cnt * digit;
            }

            start /= 10;
            n /= 10;
            digit *= 10;
        }
    }

    static void addCount(long x, long digit) {
        while (x > 0) {
            int d = (int)(x % 10);
            count[d] += digit;
            x /= 10;
        }
    }
}
