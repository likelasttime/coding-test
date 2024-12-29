import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int maxVisit = 0;
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxVisit = Math.max(arr[i], maxVisit);
        }

        if(maxVisit == 0) {
            System.out.print("SAD");
            return;
        }

        int left = 0;
        int right = x - 1;
        int maxCnt = 0;     // 가장 많이 들어온 방문자 수

        for(int i=0; i<x; i++) {
            maxCnt += arr[i];
        }

        int answerVisit = maxCnt;
        int answerCnt = 1;      // 기간 갯수
        while(right < n - 1) {
            right++;
            maxCnt -= arr[left];
            maxCnt += arr[right];
            if(answerVisit < maxCnt) {
                answerVisit = Math.max(answerVisit, maxCnt);
                answerCnt = 1;
            } else if(answerVisit == maxCnt) {
                answerCnt++;
            }
            left++;
        }

        System.out.println(answerVisit);
        System.out.println(answerCnt);
    }
}