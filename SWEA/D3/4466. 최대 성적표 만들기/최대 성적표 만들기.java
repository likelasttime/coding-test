import java.util.Scanner;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int tc=1; tc<=t; tc++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0; i<n; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            int cnt = 0;
            int answer = 0;
            int idx = n - 1;
            while(cnt < k) {
                answer += arr[idx--];
                cnt++;
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}