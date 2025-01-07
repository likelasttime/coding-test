import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();       // 수열의 크기
        int[] arr = new int[n];
        int answer = 0;
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        int x = sc.nextInt();
        Arrays.sort(arr);
        int left = 0;
        int right = n - 1;
        while(left < right) {
            int sum = arr[left] + arr[right];
            if(sum == x) {
                answer++;
            }
            if(sum > x) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(answer);
    }
}